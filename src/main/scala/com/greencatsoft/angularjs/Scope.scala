package com.greencatsoft.angularjs

import scala.scalajs.js

trait Scope extends Injectable {

  def $apply(): js.Any = ???

  def $eval(expression: String): js.Any = ???

  def $on(name: String, listener: js.Function): js.Any = ???
}

object Scope {

  val Name = "$scope"
}
