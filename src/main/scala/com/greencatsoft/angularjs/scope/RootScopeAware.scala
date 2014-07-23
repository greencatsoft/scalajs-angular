package com.greencatsoft.angularjs.scope

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait RootScopeAware extends InjectionTarget {

  implicit var rootScope: RootScope = _

  def dynamicRootScope = rootScope.asInstanceOf[js.Dynamic]

  override def dependencies = super.dependencies :+ RootScope.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(RootScope.Name) ensuring (_ >= 0)
    this.rootScope = args(index).asInstanceOf[RootScope]
  }
}