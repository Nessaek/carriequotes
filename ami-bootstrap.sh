#!/bin/bash
	#This script installs java, sbt and the application
	#Run this script on a new EC2 instance as the user-data script, which is run by `root` on machine start-up.
	set -e -x

	#Config
	export DEBIAN_FRONTEND=noninteractive

	# Update `apt-get` and install required packages
	apt-get update
	apt-get install python-software-properties
	apt-get --no-install-recommends -y install build-essential
	apt-get --no-install-recommends -y install libxslt-dev
	apt-get --no-install-recommends --fix-missing -y install git
	apt-get install unzip -y

	echo 'PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/var/lib/gems/1.8/bin"' >> /etc/environment
	echo 'DEBIAN_FRONTEND=noninteractive' >> /etc/environment

	apt-get --no-install-recommends -y -f install
	mkdir -p /tmp
	cd /tmp

	apt-get update

	#Setup Java7 JRE
	mkdir -p /usr/lib/jvm
	cd /usr/lib/jvm
	#To get around Oracle's license agreement (which can't be accepted from command line!)
	wget --no-check-certificate --no-cookies --header "Cookie: gpw_e24=test;" http://download.oracle.com/otn-pub/java/jdk/7u51-b13/jdk-7u51-linux-x64.tar.gz
	tar -xzvf jdk-7u51-linux-x64.tar.gz
	rm -rf jdk-7u51-linux-x64.tar.gz
	ln -s jdk1.7.0_51/ default-java
	update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/default-java/bin/java" 1
	update-alternatives --config java

	#Create the application specific user who would own the entire application
	useradd -m -g users -s /bin/bash -u 1001 appowner

	#Enable `appowner` user to securely login to the box
	su - appowner -c "mkdir -p /home/appowner/.ssh"
	cp -f /home/ubuntu/.ssh/authorized_keys /home/appowner/.ssh/
	chown appowner /home/appowner/.ssh/authorized_keys

	#Allow write permission on `/usr/local` for everyone. This would be needed by `appowner` to install other packages
	chmod -R a+w /usr/local

	#Create custom application space for `appowner`
	mkdir -p /mnt/appowner/application-name
	chown -R appowner /mnt/appowner

	#Setup Private key for authenticating against GitHub
	#Note: Setup and configure deploy keys in GitHub for application specific repositories
	#Note: Remember to add GitHub to the known hosts list for smooth secure communication

	# Install SBT
	wget -q apt.typesafe.com/repo-deb-build-0002.deb
	dpkg -i repo-deb-build-0002.deb
	apt-get update
	apt-get --no-install-recommends -y -f install
	apt-get update
	sleep 5
	wget http://repo.scala-sbt.org/scalasbt/sbt-native-packages/org/scala-sbt/sbt/0.12.0/sbt.deb
	dpkg -i sbt.deb
	sleep 5
	rm *.deb

	# Set JDK for `appowner` user
	su - appowner -c "echo 'export PATH="$PATH:/usr/lib/jvm/default-java/bin"' >> ~/.profile"

	#Checkout 'master' from the GitHub repo `application` and compile project
	su - appowner -c "ln -s /mnt/appowner/application-name app-server"
	su - appowner -c "cd /home/appowner && \
	git clone git@github.com:Nessaek/carriequotes.git /mnt/appowner/application-name/app-server"
	su - appowner -c "cd /home/appowner/app-server && \
	sbt clean compile"