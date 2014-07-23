package com.greencatsoft.angularjs.route

import com.greencatsoft.angularjs.NamedTarget

trait TemplateController extends NamedTarget {

  val templateUrl: String

  val title: Option[String] = None
}