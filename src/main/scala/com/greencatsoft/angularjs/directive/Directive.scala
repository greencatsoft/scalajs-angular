package com.greencatsoft.angularjs.directive

import com.greencatsoft.angularjs.NamedTarget

import scala.scalajs.js
import scala.scalajs.js.Dynamic.literal

trait Directive extends NamedTarget {

  def restrict : String = null

  def transclude : Boolean = false

  def template : String = null
  def templateFn : js.Function = null

  def templateUrl : String = null
  def templateUrlFn : js.Function = null

  def scope : Boolean = false
  def isolateScope : js.Dictionary[String] = null

  def compile : js.Function = null

  def link : js.Function = null

  // after initialize() is called, this object contains the directive definition
  protected[angularjs] val ddo = literal()


  final override def initialize() {
    if(restrict!=null)
      ddo.restrict = restrict

    if(transclude)
      ddo.transclude = transclude

    require(template==null || templateFn==null, "only one of 'template' and 'templateFn' may be defined!")
    if(template!=null)
      ddo.template = template
    if(templateFn!=null)
      ddo.template = templateFn

    require(templateUrl==null || templateUrlFn==null, "only one of 'templateUrl' or 'templateUrlFn' may be defined!")
    if(templateUrl!=null)
      ddo.templateUrl = templateUrl
    if(templateUrlFn!=null)
      ddo.templateUrl = templateUrlFn

    require( scope==false || isolateScope==null, "'scope' must be false if 'isolateScope' is defined!")
    if(scope)
      ddo.scope = true
    else if(isolateScope!=null)
      ddo.scope = isolateScope

    if(compile!=null)
      ddo.compile = compile

    if(link!=null)
      ddo.link = link

  }
}
