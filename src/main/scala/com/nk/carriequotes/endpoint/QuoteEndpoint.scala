package com.nk.carriequotes.endpoint

import scala.concurrent.{ExecutionContext, Future}

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.RejectionHandler
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import StatusCodes._
import Directives._
import akka.stream.Materializer
import com.nk.repo.QuoteRepository



trait QuoteEndpoint {

  implicit val mat: Materializer
  implicit val ec: ExecutionContext

  val repository: QuoteRepository

  val starterQuotes: List[String] = List("I wonder","I realised that","It occurred to me")
  val newsQuotes: List[String] = List("Meghan Markle","the Syrian Conflict","laissez-faire economics")
  val connectionQuotes:List[String] = List("is just like","makes you feel like")
  val problemQuotes: List[String] = List("Hanging with your girlfriends","meeting an ex","")


  val quoteRoute = {
    pathPrefix("quotes") {
        get {
          complete {
            repository.
              shuffleQuotes(starterQuotes)
          }
        }
        }

    }

  implicit def myRejectionHandler =
    RejectionHandler.newBuilder()
      .handleNotFound { complete((NotFound, "Not here!")) }
      .result()

}





