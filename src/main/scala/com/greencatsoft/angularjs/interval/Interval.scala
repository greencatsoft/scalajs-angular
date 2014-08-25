package com.greencatsoft.angularjs.interval

import com.greencatsoft.angularjs.q.Promise

import scala.scalajs.js

trait Interval extends js.Function4[js.Function,Int,Int,Boolean,Promise] {
  def cancel(promise: Promise = null) : Boolean = ???
}

object Interval {
  val Name = "$interval"
}
