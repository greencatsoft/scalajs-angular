package com.greencatsoft.angularjs.scope

import scala.scalajs.js

import com.greencatsoft.angularjs.Injectable

trait Scope extends Injectable {

  def $id : String = ???

  def $apply(exp: js.Any = null): js.Any = ???

  def $broadcast(name: String, args: js.Any*) : js.Object = ???

  def $destroy() :Unit = ???

  def $digest() : Unit = ???

  def $emit(name: String, args: js.Any*) : js.Object = ???

  def $eval(expression: js.Any = null, locals: js.Object = null): js.Any = ???

  def $evalAsync(expression: js.Any = null) : Unit = ???

  def $new(isolate: Boolean) : Scope = ???

  def $on(name: String, listener: js.Function): js.Any = ???

  def $watch(watchExpression: js.Any, listener: js.Any = null, objectEquality: Boolean = false) : js.Function = ???

  def $watchCollection(obj: js.Any, listener: js.Function) : js.Function = ???

}

object Scope {

  val Name = "$scope"
}
