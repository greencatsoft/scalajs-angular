package com.greencatsoft.angularjs

import scala.scalajs.js

import com.greencatsoft.angularjs.scope.ScopeAware

trait Controller extends NamedTarget with ScopeAware {

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    this.dynamicScope.controller = this.asInstanceOf[js.Object]
  }
}

abstract class AbstractController(_name: String = null) extends Controller {

  override lazy val name: String =
    Option(_name) getOrElse getClass.getName.split("\\$").last
}