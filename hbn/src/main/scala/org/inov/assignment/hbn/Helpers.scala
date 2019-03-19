package org.inov.assignment.hbn

import org.hibernate.{Session, SessionFactory}
import org.inov.assignment.hbn.utils.HbnUtils

object Helpers {

  def runInTransaction(sessFactory:SessionFactory, action: Session => Unit):Unit = {
    val sess = sessFactory.getCurrentSession
    val tr = sess.beginTransaction()
    try {
      action(sess)
      tr.commit()
    }
    catch {
      case t:Throwable => {
        tr.rollback()
        //        t.printStackTrace()
        throw t
      }
    }
  }

  def runInTransaction[T](sessFactory:SessionFactory, f: Session => T):T = {
    val sess = sessFactory.getCurrentSession
    val tr = sess.beginTransaction()
    try {
      val res = f(sess)
      tr.commit()
      res
    }
    catch {
      case t:Throwable => {
        tr.rollback()
        //        t.printStackTrace()
        throw t
      }
    }
  }

  def testRunInTransaction[T](f: Session => T):T = {
    runInTransaction(HbnUtils.testSessFactory, f)
  }


  def loggingTodo(msg:String):Unit = {
    println(msg)
  }
}
