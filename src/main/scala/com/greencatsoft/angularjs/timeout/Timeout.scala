package com.greencatsoft.angularjs.timeout

import scala.scalajs.js

import com.greencatsoft.angularjs.Injectable
import com.greencatsoft.angularjs.q.Promise

trait Timeout extends Injectable {

  def apply(fn: js.Function0[_], delay: Int = 0, invokeApply: Boolean = true): Promise = ???

  def cancel(promise: Promise = null): Boolean = ???
}

object Timeout {

  val Name = "$timeout"
}
