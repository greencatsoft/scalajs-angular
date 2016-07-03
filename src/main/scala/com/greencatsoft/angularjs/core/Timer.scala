package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

@js.native
@injectable("$interval")
trait Interval extends js.Object {

  def apply[T](fn: js.Function0[T], delay: Int = 0, invokeApply: Boolean = true): Promise[T] = js.native

  def cancel(promise: Promise[_] = null): Boolean = js.native
}

@js.native
@injectable("$timeout")
trait Timeout extends js.Object {

  def apply[T](fn: js.Function0[T], delay: Int = 0, invokeApply: Boolean = true): Promise[T] = js.native

  def cancel(promise: Promise[_] = null): Boolean = js.native
}
