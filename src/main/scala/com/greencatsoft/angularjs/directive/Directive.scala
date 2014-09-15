package com.greencatsoft.angularjs.directive

import scala.scalajs.js
import scala.scalajs.js.Any.{fromFunction4, wrapArray}
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.{ConfigBuilder, Module, NamedTarget}
import com.greencatsoft.angularjs.controller.Controller
import com.greencatsoft.angularjs.scope.Scoped

trait Directive extends NamedTarget with Scoped with ConfigBuilder {

  override def initialize(): Unit = Unit

  val controller: Option[Controller] = None

  override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    def bind(scope: ScopeType): ScopeType = {
      scope.dynamic.directive = this.asInstanceOf[js.Object]
      scope
    }

    config("link") = (scope: ScopeType, elems: js.Array[Element], attrs: Attributes, controllers: UndefOr[js.Any]) => {
      controllers.toOption match {
        case Some(arr) if js.Array.isArray(arr) =>
          val args = arr.asInstanceOf[js.Array[js.Any]].toSeq.map(Module.unbindTarget[Controller](_)).flatten
          link(bind(scope), elems, attrs, args: _*)
        case Some(c) =>
          Module.unbindTarget[Controller](c) match {
            case Some(arg) => link(bind(scope), elems, attrs, arg)
            case _ => link(bind(scope), elems, attrs)
          }
        case None => link(bind(scope), elems, attrs)
      }
    }

    controller foreach { c =>
      Module.asService(c) { args: js.Array[js.Any] => config("controller") = args }
    }

    super.buildConfig(config)
  }

  def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes, controller: Controller*): Unit = Unit
}

