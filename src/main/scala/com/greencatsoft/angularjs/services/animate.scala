package com.greencatsoft.angularjs.services

import com.greencatsoft.angularjs.InjectionTarget

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

trait AnimateAware extends InjectionTarget {
  implicit var animate: Animate = _

  override def dependencies = super.dependencies :+ Animate.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Animate.Name) ensuring (_ >= 0)
    this.animate = args(index).asInstanceOf[Animate]
  }
}