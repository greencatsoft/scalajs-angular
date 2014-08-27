package com.greencatsoft.angularjs.scope

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait ScopeAware extends InjectionTarget {

  type ScopeType <: Scope

  var currentScope: Option[ScopeType] = None

  override def dependencies = super.dependencies :+ Scope.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Scope.Name) ensuring (_ >= 0)

    val scope = args(index).asInstanceOf[ScopeType]
    scope.dynamic.controller = this.asInstanceOf[js.Object]

    this.currentScope = Some(scope)
  }

  override def initialize(): Unit = currentScope.foreach(initialize(_))

  def initialize(scope: ScopeType): Unit = Unit

  implicit class DynamicScope(scope: ScopeType) {

    def dynamic = scope.asInstanceOf[js.Dynamic]
  }
}