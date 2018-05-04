package com.nk.carriequotes.repo

import scala.util.Random

class QuoteRepository {

  def shuffleQuotes(quotes:List[String]): String = {
    val shuffledArray = Random.shuffle(quotes)
      shuffledArray(0)
  }


}
