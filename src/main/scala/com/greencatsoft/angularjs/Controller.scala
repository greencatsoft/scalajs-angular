package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.{ Scope, ScopeOps }

import scala.scalajs.js

trait Controller[A <: Scope] extends Service with ScopeOps {

  def scope: A

  abstract override def initialize() {
    assert(scope != null, "Property 'scope' should not be null.")

    super.initialize()
    scope.dynamic.controller = this.asInstanceOf[js.Object]
  }
}

abstract class AbstractController[A <: Scope](override val scope: A) extends Controller[A]
