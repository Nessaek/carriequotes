package com.nk.carriequotes

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.nk.carriequotes.endpoint.QuoteEndpoint
import com.nk.carriequotes.repo.QuoteRepository
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext


object Main extends App with QuoteEndpoint {


  implicit val sys: ActorSystem = ActorSystem("carriequotes")
  implicit val mat: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = sys.dispatcher
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")


  override val repository: QuoteRepository = new QuoteRepository()

  Http().bindAndHandle(handler = quoteRoute, interface = "0.0.0.0", port = 8080) map { binding =>
    println(s"REST interface bound to ${binding.localAddress}") } recover { case ex =>
    println(s"REST interface could not bind to $host:$port", ex.getMessage)
  }
}
