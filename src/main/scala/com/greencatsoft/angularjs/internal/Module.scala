package com.greencatsoft.angularjs.internal

import com.greencatsoft.angularjs.ServiceDefinition

import scala.scalajs.js

@js.native
private[angularjs] trait Module extends js.Object {

  def factory(name: String, constructor: ServiceDefinition[_]): Module = js.native

  def controller(name: String, constructor: ServiceDefinition[_]): Module = js.native

  def service(name: String, constructor: ServiceDefinition[_]): Module = js.native

  def config(constructor: ServiceDefinition[_]): Module = js.native

  def run(constructor: ServiceDefinition[_]): Module = js.native

  def directive(name: String, directiveFactory: ServiceDefinition[_]): Module = js.native

  def filter(name: String, constructor: ServiceDefinition[_]): Module = js.native
}
