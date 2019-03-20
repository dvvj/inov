package org.inov.assignment.gnl

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import scalaj.http.{Http, HttpOptions, HttpResponse}

object SvcHelpers {

  private def testGetAllUrl(host:String):String = s"$host/customer/testGetAll"
  private def newCustomerUrl(host:String):String = s"$host/customer/create"
  private def customerByNameUrl(host:String, name:String):String = {
    val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.name())
    s"$host/customer/byName/$encodedName"
  }

  private def getStr(url:String):String = {
    val res = Http(url)
      .option(HttpOptions.allowUnsafeSSL)
      .asString
    if (res.isSuccess)
      res.body
    else
      throw new RuntimeException("Failed to get response from ")

  }

  def getAllCustomers(host:String):String = {
    getStr(testGetAllUrl(host))
  }

  def customerByName(host:String, name:String):String = {
    getStr(customerByNameUrl(host, name))
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
