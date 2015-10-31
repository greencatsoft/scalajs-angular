package com.greencatsoft.angularjs.internal

import scala.scalajs.js

@js.native
private[angularjs] trait Module extends js.Object {

  def factory(name: String, constructor: js.Array[js.Any]): Module = js.native

  def controller(name: String, constructor: js.Array[js.Any]): Module = js.native

  def service(name: String, constructor: js.Array[js.Any]): Module = js.native

  def config(constructor: js.Array[js.Any]): Module = js.native

  def run(constructor: js.Array[js.Any]): Module = js.native

  def directive(name: String, directiveFactory: js.Array[js.Any]): Module = js.native

  def filter(name: String, constructor: js.Array[js.Any]): Module = js.native
}
