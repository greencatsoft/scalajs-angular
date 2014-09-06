package com.greencatsoft.angularjs.scope

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait RootScopeAware extends InjectionTarget with Scoped {

  implicit var rootScope: ScopeType = _

  override type ScopeType <: RootScope

  override def dependencies = super.dependencies :+ RootScope.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(RootScope.Name) ensuring (_ >= 0)
    this.rootScope = args(index).asInstanceOf[ScopeType]
  }

  implicit class DynamicScope(scope: ScopeType) {

    def dynamic = scope.asInstanceOf[js.Dynamic]
  }
}