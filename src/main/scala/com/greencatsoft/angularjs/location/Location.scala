package com.greencatsoft.angularjs.location

import scala.scalajs.js

trait Location extends js.Object {

  def absUrl(): String = ???

  def protocol(): String = ???

  def host(): String = ???

  def port(): Int = ???

  def path(): String = ???

  def hash(): String = ???
}

object Location {

  val Name = "$location"
}
