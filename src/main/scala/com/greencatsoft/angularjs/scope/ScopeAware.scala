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
    this.currentScope = Some(args(index).asInstanceOf[ScopeType])
  }

  override def initialize(): Unit = currentScope.foreach(initialize(_))

  def initialize(scope: ScopeType): Unit

  implicit class DynamicScope(scope: ScopeType) {

    def dynamic = scope.asInstanceOf[js.Dynamic]
  }
}