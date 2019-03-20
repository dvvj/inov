package org.inov.assignment.srcTests

import org.inov.assignment.gnl.SvcHelpers
import org.inov.assignment.hbn.ent.Customer
import org.inov.assignment.hbn.utils.HbnUtils

object CustomerTests extends App {
  var customers =
    List(
      new Customer("customer1", "John Smith", "M"),
      new Customer("customer2", "Erik Lövquist", "M"),
      new Customer("customer3", "やまた", "M"),
      new Customer("customer4", "张晓", "F"),
      new Customer("customer4", "张晓", "M"),
    )

  val svcEndpoint = "https://localhost:8443/webapi"

  customers.foreach { c =>
    val j = HbnUtils.toJson(c)
    val success = SvcHelpers.newCustomer(svcEndpoint, j)
    if (success)
      println(s"Customer ${c.getUid} added")
    else
      println(s"Failed to add customer: ${c.getUid}")
  }

  val resp = SvcHelpers.getAllCustomers(svcEndpoint)

  println(resp)


}
