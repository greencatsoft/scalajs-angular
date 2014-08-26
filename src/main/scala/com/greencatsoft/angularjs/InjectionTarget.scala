package com.greencatsoft.angularjs

import scala.scalajs.js

trait InjectionTarget {

  def dependencies: Seq[String] = Nil

  def inject(args: Seq[js.Any]): Unit = None

  def initialize(): Unit
}
