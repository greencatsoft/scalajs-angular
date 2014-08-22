package com.greencatsoft.angularjs.directive

import com.greencatsoft.angularjs.NamedTarget

import scala.scalajs.js
import scala.scalajs.js.Dynamic.literal

trait Directive extends NamedTarget {

  def restrict : String = null

  def transclude : Boolean = false

  def template : String = null
  def templateFn : js.Function2[Dynamic,Dynamic,String] = null

  def templateUrl : String = null
  def templateUrlFn : js.Function2[Dynamic,Dynamic,String] = null

  def scope : Boolean = false
  def isolateScope : js.Dictionary[String] = null

  // TODO: replace Dynamic arguments with typed arguments?
  def compile : js.Function3[Dynamic,Dynamic,Dynamic,Unit] = null
  def compileWithReturn : js.Function3[Dynamic,Dynamic,Dynamic,js.Object] = null

  // TODO: replace Dynamic arguments with typed arguments?
  def link : js.Function5[Dynamic,Dynamic,Dynamic,Dynamic,Dynamic,Unit] = null

  // after initialize() is called, this object contains the directive definition
  protected[angularjs] val ddo = literal()


  final override def initialize() {
    if(restrict!=null)
      ddo.restrict = restrict

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

    require( scope==false || isolateScope==null, "'scope' must be false is 'isolateScope' is defined!")
    if(scope)
      ddo.scope = true
    if(isolateScope!=null)
      ddo.scope = isolateScope

    require(compile==null || compileWithReturn==null, "only one of 'compile' or 'compileWithReturn' may be defined!")
    if(compile!=null)
      ddo.compile = compile
    if(compileWithReturn!=null)
      ddo.compile = compileWithReturn

    if(link!=null)
      ddo.link = link

  }
}
