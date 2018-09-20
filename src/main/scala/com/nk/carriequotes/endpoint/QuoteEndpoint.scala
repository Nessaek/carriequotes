package com.nk.carriequotes.endpoint

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import Directives._
import com.nk.carriequotes.repo.QuoteRepository
import akka.stream.Materializer
import org.omg.CosNaming.NamingContextPackage.NotFound


trait QuoteEndpoint {

  implicit val mat: Materializer
  implicit val ec: ExecutionContext

  val repository: QuoteRepository

  val mappedQuote: Map[Int, List[String]] = Map(0-> List("I wonder","I realised that","It occurred to me"), 1 -> List("Meghan Markle","the Syrian Conflict","laissez-faire economics"), 2 -> List("is just like","makes you feel like"), 3 -> List("Hanging with your girlfriends","meeting an ex"))



  val quoteRoute: Route = cors(){
    pathPrefix("quote") {
        get {
          complete (201 -> repository.shuffleQuotes(mappedQuote))
        }
        }
    }

  implicit def myRejectionHandler =
    RejectionHandler.newBuilder()
      .handleNotFound { complete((404, "Not here!")) }
      .result()

}





