package org.inov.assignment.srcTests

import org.inov.assignment.gnl.SvcHelpers

object CustomerTests extends App {

  val resp = SvcHelpers.get("https://localhost:8443/webapi/customer/all")

  println(resp)


}
