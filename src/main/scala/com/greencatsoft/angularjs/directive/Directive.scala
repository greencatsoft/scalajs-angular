package com.greencatsoft.angularjs.directive

import scala.scalajs.js
import scala.scalajs.js.Any.{ fromFunction4, wrapArray }
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.{ ConfigBuilder, NamedTarget }
import com.greencatsoft.angularjs.controller.Controller
import com.greencatsoft.angularjs.scope.Scoped

trait Directive extends NamedTarget with Scoped with ConfigBuilder {

  type ControllerType <: Controller

  override def initialize(): Unit = Unit

  override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("link") = (scope: ScopeType, elems: js.Array[Element], attrs: Attributes, controller: UndefOr[ControllerType]) => {
      scope.dynamic.directive = this.asInstanceOf[js.Object]

      if (controller.isDefined)
        link(scope, elems, attrs, controller.get)
      else
        link(scope, elems, attrs)
    }

    super.buildConfig(config)
  }

  def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes, controller: ControllerType) {
    link(scope, elems, attrs)
  }

  def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes): Unit = Unit
}

