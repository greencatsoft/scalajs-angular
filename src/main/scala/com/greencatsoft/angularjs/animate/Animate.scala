package com.greencatsoft.angularjs.animate

import scala.scalajs.js

import com.greencatsoft.angularjs.Injectable

trait Animate extends Injectable {

  def enter(element: js.Object, parent: js.Object, after: js.Object, done: js.Function = null): Unit = ???

  def leave(element: js.Object, done: js.Function = null): Unit = ???

  def move(element: js.Object, parent: js.Object, after: js.Object, done: js.Function = null): Unit = ???

  def addClass(element: js.Object, className: String, done: js.Function = null): Unit = ???

  def removeClass(element: js.Object, className: String, done: js.Function = null): Unit = ???

  def setClass(element: js.Object, add: String, remove: String, done: js.Function = null): Unit = ???
}

object Animate {

  val Name = "$animate"
}

