package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.{ Initializable, Service, inject, injectable }

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

  def $on(name: String, listener: js.Function): js.Any = js.native

  def $watch(watchExpression: js.Any, listener: js.Any = null, objectEquality: Boolean = false): js.Function = js.native

  def $watchCollection(obj: js.Any, listener: js.Function): js.Function = js.native
}

trait Scoped {
  this: Service =>

  type ScopeType <: Scope

  implicit class DynamicScope(scope: ScopeType) {

    def dynamic = scope.asInstanceOf[js.Dynamic]
  }
}

trait ScopeAware extends Initializable with Scoped {
  this: Service =>

  @inject
  var rawScope: Scope = _

  implicit var scope: ScopeType = _

  abstract override def initialize() {
    this.scope = rawScope.asInstanceOf[ScopeType]

    super.initialize()

    initialize(scope)
  }

  def initialize(scope: ScopeType): Unit = Unit
}

@injectable("$rootScope")
trait RootScope extends Scope

trait RootScopeAware extends Initializable with Scoped {
  this: Service =>

  @inject
  var rawRootScope: RootScope = _

  implicit var rootScope: ScopeType = _

  override type ScopeType <: RootScope

  abstract override def initialize() {
    this.rootScope = rawRootScope.asInstanceOf[ScopeType]

    super.initialize()
  }
}
