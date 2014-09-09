package com.greencatsoft.angularjs.scope

import scala.scalajs.js

trait Scoped {

  type ScopeType <: Scope

  implicit class DynamicScope(scope: ScopeType) {

    def dynamic = scope.asInstanceOf[js.Dynamic]
  }
}
