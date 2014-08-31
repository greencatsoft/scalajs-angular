package com.greencatsoft.angularjs.scope

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait ScopeAware extends InjectionTarget with Scoped {

  implicit var scope: ScopeType = _

  def dynamicScope = scope.asInstanceOf[js.Dynamic]

  override def dependencies = super.dependencies :+ Scope.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Scope.Name) ensuring (_ >= 0)
    this.scope = args(index).asInstanceOf[ScopeType]
  }
}