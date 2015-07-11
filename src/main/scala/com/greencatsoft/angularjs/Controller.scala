package com.greencatsoft.angularjs

import scala.language.experimental.macros
import scala.scalajs.js

import com.greencatsoft.angularjs.core.{ Scope, ScopeOps }
import com.greencatsoft.angularjs.internal.ServiceProxy

trait Controller[A <: Scope] extends Service with ScopeOps {

  def scope: A

  abstract override def initialize() {
    assert(scope != null, "Property 'scope' should not be null.")

    super.initialize()
    scope.dynamic.controller = this.asInstanceOf[js.Object]
  }
}

object Controller {

  def proxy[A <: Controller[_]]: js.Any = macro ServiceProxy.newClassWrapper[A]

  def proxy[A <: Controller[_]](target: A): js.Any = macro ServiceProxy.newObjectWrapper[A]
}

abstract class AbstractController[A <: Scope](override val scope: A) extends Controller[A]