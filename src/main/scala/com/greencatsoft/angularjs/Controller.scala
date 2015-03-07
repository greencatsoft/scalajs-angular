package com.greencatsoft.angularjs

import scala.scalajs.js

import com.greencatsoft.angularjs.core.ScopeAware

trait Controller extends Service with Initializable with ScopeAware {

  abstract override def initialize() {
    super.initialize()
    scope.dynamic.controller = this.asInstanceOf[js.Object]
  }
}
