package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.injectable

@injectable("$interval")
trait Interval extends js.Object {

  def apply(fn: js.Function0[_], delay: Int = 0, invokeApply: Boolean = true): Promise = js.native

  def cancel(promise: Promise = null): Boolean = js.native
}

@injectable("$timeout")
trait Timeout extends js.Object {

  def apply(fn: js.Function0[_], delay: Int = 0, invokeApply: Boolean = true): Promise = js.native

  def cancel(promise: Promise = null): Boolean = js.native
}
