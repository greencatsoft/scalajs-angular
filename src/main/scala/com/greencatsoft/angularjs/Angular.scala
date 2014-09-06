package com.greencatsoft.angularjs

import scala.language.implicitConversions
import scala.scalajs.js

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.element.AngularElement

trait Angular extends js.Object {

  def module(name: String, require: js.Array[String]): Module = ???

  implicit def element(elem: Element): AngularElement
}
