package com.greencatsoft.angularjs.controller

trait TemplateController extends Controller {

  val templateUrl: String

  val title: Option[String] = None
}