package com.greencatsoft.angularjs.timer

import scala.scalajs.js
import com.greencatsoft.angularjs.Injectable
import com.greencatsoft.angularjs.q.Promise

trait Interval extends Injectable {

  def apply(fn: js.Function0[_], delay: Int = 0, invokeApply: Boolean = true): Promise = ???

  def cancel(promise: Promise = null): Boolean = ???
}

object Interval {

  val Name = "$interval"
}
