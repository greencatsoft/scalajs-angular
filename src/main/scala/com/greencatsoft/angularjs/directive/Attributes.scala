package com.greencatsoft.angularjs.directive

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSBracketAccess

trait Attributes extends js.Object {

  val $attr: js.Dictionary[String] = ???

  def $addClass(classVal: String): Unit = ???

  def $removeClass(classVal: String): Unit = ???

  def $updateClass(newClasses: String, oldClasses: String): Unit = ???

  @JSBracketAccess
  def apply(name: String): UndefOr[String] = ???

  @JSBracketAccess
  def update(name: String, value: String): Unit = ???

  def $get(name: String): UndefOr[String] = ???

  def $set(name: String, value: String): Unit = ???

  def $observe(key: String, fn: js.Function1[String, Unit]): Unit = ???
}
