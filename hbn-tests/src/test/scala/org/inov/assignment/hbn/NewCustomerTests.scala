package org.inov.assignment.hbn


import org.inov.assignment.hbn.utils.HbnUtils

import scala.concurrent.{Await, Future}
import scala.util.Random

object NewCustomerTests extends App {

  import HbnDbOpsImpl._

  testHbnOps.addNewCustomer(
    "cid1", "name1", "M"
  )

  testHbnOps.addNewCustomer(
    "cid2", "name2", "M"
  )

  val queryResult = testHbnOps.queryByName("name2")
  println(queryResult.size())

  HbnUtils.shutdownTest()
}
