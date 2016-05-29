package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.{ Scope, ScopeOps }

import scala.scalajs.js

trait Controller[A <: Scope] extends Service with ScopeOps {

  def scope: A

  /** Name to bind the controller to (instead of the default name of `controller`). */
  def controllerAs: Option[String] = None

  abstract override def initialize() {
    assert(scope != null, "Property 'scope' should not be null.")

    super.initialize()
    scope.dynamic.updateDynamic(controllerAs.getOrElse("controller"))(this.asInstanceOf[js.Object])
  }
}

abstract class AbstractController[A <: Scope](override val scope: A, override val controllerAs: Option[String] = None)
  extends Controller[A]
