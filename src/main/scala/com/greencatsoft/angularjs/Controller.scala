package com.greencatsoft.angularjs

import scala.scalajs.js

import com.greencatsoft.angularjs.core.{ Scope, ScopeOps }

trait Controller[A <: Scope] extends Service with ScopeOps {

  def scope: A

  abstract override def initialize() {
    assert(scope != null, "Property 'scope' should not be null.")

    super.initialize()
    scope.dynamic.controller = this.asInstanceOf[js.Object]
  }
}

abstract class AbstractController[A <: Scope](override val scope: A) extends Controller[A]