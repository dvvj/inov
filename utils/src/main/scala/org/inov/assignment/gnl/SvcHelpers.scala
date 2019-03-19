package org.inov.assignment.gnl

import scalaj.http.{Http, HttpOptions}

object SvcHelpers {

  def get(url:String):String = {
    val res = Http(url)
      .option(HttpOptions.allowUnsafeSSL)
      .asString
    res.body
  }


}
