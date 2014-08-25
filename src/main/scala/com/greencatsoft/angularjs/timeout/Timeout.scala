package com.greencatsoft.angularjs.timeout

import com.greencatsoft.angularjs.q.Promise
import scala.scalajs.js

trait Timeout extends js.Function3[js.Function,Int,Boolean,Promise] {
  def cancel(promise: Promise = null) : Boolean= ???
}

object Timeout {
  val Name = "$timeout"
}
