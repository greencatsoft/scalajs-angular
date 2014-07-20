package com.greencatsoft.angularjs

trait TemplateController extends NamedTarget{

  val templateUrl: String
  
  val title: Option[String] = None
}