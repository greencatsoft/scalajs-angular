package com.greencatsoft.angularjs

trait TemplateController extends Controller {

  val templateUrl: String

  val title: Option[String] = None
}