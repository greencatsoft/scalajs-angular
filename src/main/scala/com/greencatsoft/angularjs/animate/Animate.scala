package com.greencatsoft.angularjs.animate

import scala.scalajs.js

trait Animate extends js.Object {

  def addClass(element: js.Object, className: String, done: js.Function = null) : Unit = ???

  def enter(element: js.Object, parent: js.Object, after: js.Object, done: js.Function = null) : Unit = ???

  def leave(element: js.Object, done: js.Function = null) : Unit = ???

  def move(element: js.Object, parent: js.Object, after: js.Object, done: js.Function = null) : Unit = ???

  def removeClass(element: js.Object, className: String, done: js.Function = null) : Unit = ???

  def setClass(element: js.Object, add: String, remove: String, done: js.Function = null) : Unit = ???
}

object Animate {
  val Name = "$animate"
}

