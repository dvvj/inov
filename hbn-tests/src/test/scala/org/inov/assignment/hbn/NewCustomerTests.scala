package org.inov.assignment.hbn


import org.inov.assignment.hbn.ent.Customer
import org.inov.assignment.hbn.utils.HbnUtils


object NewCustomerTests extends App {

  import HbnDbOpsImpl._

  testHbnOps.addNewCustomer(
    new Customer("cid1", "John Smith", "M")
  )

  testHbnOps.addNewCustomer(
    new Customer("cid2", "name2", "M")
  )

  val queryResult = testHbnOps.queryByName("John Smith")
  println(queryResult.size())

  HbnUtils.shutdownTest()
}
