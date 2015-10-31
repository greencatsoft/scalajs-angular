package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.injectable

@js.native
@injectable("$animate")
trait Animate extends js.Object {

  def enter(element: js.Object, parent: js.Object, after: js.Object, done: js.Function = null): Unit = js.native

  def leave(element: js.Object, done: js.Function = null): Unit = js.native

  def move(element: js.Object, parent: js.Object, after: js.Object, done: js.Function = null): Unit = js.native

  def addClass(element: js.Object, className: String, done: js.Function = null): Unit = js.native

  def removeClass(element: js.Object, className: String, done: js.Function = null): Unit = js.native

  def setClass(element: js.Object, add: String, remove: String, done: js.Function = null): Unit = js.native
}
