package org.inov.assignment.hbn

import java.util

import org.hibernate.{Session, SessionFactory}
import org.inov.assignment.gnl.DataUtils
import org.inov.assignment.hbn.ent.Customer
import org.inov.assignment.hbn.utils.HbnUtils

import scala.reflect.ClassTag

object HbnDbOpsImpl {

  import Helpers._

  import collection.JavaConverters._

  private def queryAndConvert[TFrom : ClassTag, TTo : ClassTag](sess:Session, className:String, converter:TFrom => TTo):Array[TTo] = {
    val ql = s"Select x from $className x"
    val q = sess.createQuery(ql)
    val t = q.getResultList.asScala.map(_.asInstanceOf[TFrom])
    //        t.foreach { c => println(AuthHelpers.hash2Str(c.getPassHash)) }
    val res = t.map(converter).toArray
    res
  }


  private class OpsImpl(sessFactory:SessionFactory) extends IDbOps {
    override def addNewCustomer(
                                 uid: String,
                                 name: String,
                                 gender:String
                               ): String = {
      runInTransaction(
        sessFactory,
        { sess =>
          val customer = new Customer(
            uid, name, "F"
          )
          sess.save(customer)
          customer.getUid
        }
      )
    }

    import collection.JavaConverters._
    override def queryByName(name: String): java.util.List[Customer] = {
      runInTransaction(
        sessFactory,
        { sess =>
          val customers = sess.createQuery("FROM Customer").getResultList.asScala
            .map(_.asInstanceOf[Customer])
            .toList.asJava
          customers
        }
      )
    }
  }

  def hbnOps(cfgFile:String):IDbOps = new OpsImpl(
    HbnUtils.getSessFactory(cfgFile)
  )

  val testHbnOps:IDbOps = new OpsImpl(HbnUtils.testSessFactory)
}
