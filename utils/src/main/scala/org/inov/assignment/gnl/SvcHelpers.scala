package org.inov.assignment.gnl

import scalaj.http.{Http, HttpOptions}

object SvcHelpers {

  private def testGetAllUrl(host:String):String = s"$host/customer/testGetAll"
  private def newCustomerUrl(host:String):String = s"$host/customer/create"

  def getAllCustomers(host:String):String = {
    val res = Http(testGetAllUrl(host))
      .option(HttpOptions.allowUnsafeSSL)
      .asString
    res.body
  }

  def put(url:String, data:String):Boolean = {
    val res = Http(url)
      .option(HttpOptions.allowUnsafeSSL)
      .put(data)
      .method("PUT")
      .headers(
        Map(
          "content-type" -> "text/plain;charset=utf-8"
        )
      )
      .asString
    res.isSuccess
  }

  def newCustomer(host:String, data:String):Boolean = {
    put(newCustomerUrl(host), data)
  }

}
