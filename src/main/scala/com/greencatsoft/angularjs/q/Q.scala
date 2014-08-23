package com.greencatsoft.angularjs.q

import scala.scalajs.js

trait Q extends js.Object {

  def defer(): Defer = ???

  def promise(): Promise = ???
}

object Q {

  val Name = "$q"
}
