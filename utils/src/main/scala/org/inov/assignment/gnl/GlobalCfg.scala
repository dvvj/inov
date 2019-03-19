package org.inov.assignment.gnl

import java.nio.charset.StandardCharsets

import org.apache.commons.io.IOUtils
import org.json4s.DefaultFormats

case class GlobalCfg(
  svcSvr:String,
  timeOutMs:Int,
  infoDbSvr:String,
  assetLocalPath:String
) {
  import GlobalCfg._

  def allCustomersURL:String = s"$svcSvr/$ALLCUSTOMERS"
}

object GlobalCfg {

  private val ALLCUSTOMERS = "customer/all"

  def fromJson(j:String):GlobalCfg = {
    import org.json4s.jackson.JsonMethods._
    implicit val fmt = DefaultFormats
    parse(j).extract[GlobalCfg]
  }

  val localTestCfg:GlobalCfg = {
    val strm = getClass.getResourceAsStream("/localTestCfg.json")
    val j = IOUtils.toString(strm, StandardCharsets.UTF_8)
    strm.close()
    fromJson(j)
  }
}