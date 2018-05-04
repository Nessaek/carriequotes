package com.nk.carriequotes.endpoint

import scala.concurrent.{ExecutionContext}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._

import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import Directives._
import com.nk.carriequotes.repo.QuoteRepository
import akka.stream.Materializer


trait QuoteEndpoint {

  implicit val mat: Materializer
  implicit val ec: ExecutionContext

  val repository: QuoteRepository

  val starterQuotes: List[String] = List("I wonder","I realised that","It occurred to me")
  val newsQuotes: List[String] = List("Meghan Markle","the Syrian Conflict","laissez-faire economics")
  val connectionQuotes:List[String] = List("is just like","makes you feel like")
  val problemQuotes: List[String] = List("Hanging with your girlfriends","meeting an ex","")



  val quoteRoute: Route = cors(){
    pathPrefix("quote") {
        get {
//          complete (201 -> "test")
          complete (201 -> "tes11t")
        }
        }
    }

//  implicit def myRejectionHandler =
//    RejectionHandler.newBuilder()
//      .handleNotFound { complete((NotFound, "Not here!")) }
//      .result()

}





