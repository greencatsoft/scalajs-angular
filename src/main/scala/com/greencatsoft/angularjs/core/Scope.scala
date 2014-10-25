package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.{ Initializable, Service, inject, injectable }

@injectable("$scope")
trait Scope extends js.Object {

  def $id: String = ???

  def $apply(exp: js.Any = null): js.Any = ???

  def $broadcast(name: String, args: js.Any*): js.Object = ???

  def $destroy(): Unit = ???

  def $digest(): Unit = ???

  def $emit(name: String, args: js.Any*): js.Object = ???

  def $eval(expression: js.Any = null, locals: js.Object = null): js.Any = ???

  def $evalAsync(expression: js.Any = null): Unit = ???

  def $new(isolate: Boolean): Scope = ???

  def $on(name: String, listener: js.Function): js.Any = ???

  def $watch(watchExpression: js.Any, listener: js.Any = null, objectEquality: Boolean = false): js.Function = ???

  def $watchCollection(obj: js.Any, listener: js.Function): js.Function = ???
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