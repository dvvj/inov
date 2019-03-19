package org.inov.assignment.srcTests

import org.inov.assignment.gnl.{GlobalCfg, SvcHelpers}

object CustomerTests extends App {

  val cfg = GlobalCfg.localTestCfg


  val resp = SvcHelpers.get(cfg.allCustomersURL)

  println(resp)


}
