package com.greencatsoft.angularjs.scope

import scala.scalajs.js

import com.greencatsoft.angularjs.Injectable

trait Scope extends Injectable {

  def $apply(): js.Any = ???

  def $eval(expression: String): js.Any = ???

  def $on(name: String, listener: js.Function): js.Any = ???
}

object Scope {

  val Name = "$scope"
}
