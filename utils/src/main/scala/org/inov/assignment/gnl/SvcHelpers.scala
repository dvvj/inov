package org.inov.assignment.gnl

import scalaj.http.{Http, HttpOptions}

object SvcHelpers {

  def get(url:String):String = {
    val res = Http(url)
      .option(HttpOptions.allowUnsafeSSL)
      .asString
    res.body
  }

  def put(url:String, data:String):String = {
    val res = Http(url)
      .option(HttpOptions.allowUnsafeSSL)
      .put(data)
      .method("PUT")
      .headers(
        Map(
          "content-type" -> "text/plain"
        )
      )
      .asString
    res.body
  }

}
