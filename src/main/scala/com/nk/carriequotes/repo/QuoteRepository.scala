package com.nk.carriequotes.repo

import scala.util.Random

class QuoteRepository {
  val starterQuotes: List[String] = List("I wonder if","I realised that","It occurred to me")
  val newsQuotes: List[String] = List("Meghan Markle","the Syrian Conflict","laissez-faire economics")
  val connectionQuotes:List[String] = List("is just like","makes you feel like")
  val problemQuotes: List[String] = List("hanging with your girlfriends","meeting an ex","")


  def shuffleQuotes(quotes:Map[Int, List[String]]): String = {
    val random = new Random()
 quotes.map{
      case (key,_) => quotes(key)(random.nextInt(quotes(key).length))
    }.reduceLeft(_ + " " + _)
  }


}
