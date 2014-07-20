package com.greencatsoft.angularjs

import scala.scalajs.js

trait ScopeAware extends InjectionTarget {

  type DataType

  implicit var scope: Scope with DataType = _

  def dynamicScope = scope.asInstanceOf[js.Dynamic]

  override def dependencies = super.dependencies :+ Scope.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Scope.Name) ensuring (_ >= 0)
    this.scope = args(index).asInstanceOf[Scope with DataType]
  }
}