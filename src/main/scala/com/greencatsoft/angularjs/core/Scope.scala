package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

@js.native
@injectable("$scope")
trait Scope extends js.Object {

  def $id: String = js.native

  def $apply(exp: js.Any = null): js.Any = js.native

  def $broadcast(name: String, args: js.Any*): js.Object = js.native

  def $destroy(): Unit = js.native

  def $digest(): Unit = js.native

  def $emit(name: String, args: js.Any*): js.Object = js.native

  def $eval(expression: js.Any = null, locals: js.Object = null): js.Any = js.native

  def $evalAsync(expression: js.Any = null): Unit = js.native

  def $new(isolate: Boolean): Scope = js.native

  def $on(name: String, listener: js.Function): js.Function0[Unit] = js.native

  def $watch(watchExpression: js.Any, listener: js.Any = null, objectEquality: Boolean = false): js.Function0[Unit] = js.native

  def $watchCollection(obj: js.Any, listener: js.Function): js.Function = js.native
}

@js.native
@injectable("$rootScope")
trait RootScope extends Scope

trait ScopeOps {

  implicit class DynamicScope(scope: Scope) {

    def dynamic = scope.asInstanceOf[js.Dynamic]
  }
}

@js.native
trait Event extends js.Object {

  val name: String = js.native

  val defaultPrevented: Boolean = js.native

  def preventDefault(): Unit = js.native

  def stopPropagation(): Unit = js.native

  def currentScope[A <: Scope]: A = js.native

  def targetScope[A <: Scope]: A = js.native
}
