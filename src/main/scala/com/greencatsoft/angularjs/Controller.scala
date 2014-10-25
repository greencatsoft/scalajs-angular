package com.greencatsoft.angularjs

import scala.scalajs.js

import com.greencatsoft.angularjs.core.ScopeAware

trait Controller extends NamedService with Initializable with ScopeAware {

  abstract override def initialize() {
    super.initialize()
    scope.dynamic.controller = this.asInstanceOf[js.Object]
  }
}

trait PageController extends Controller with Templated

trait TitledPageController extends PageController with Titled
