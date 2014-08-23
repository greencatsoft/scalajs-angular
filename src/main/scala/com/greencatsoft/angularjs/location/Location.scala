package com.greencatsoft.angularjs.location

import scala.scalajs.js

trait Location extends js.Object {

  def absUrl(): String = ???

  def url(url: String = null, replace: String = null) : String = ???

  def protocol(): String = ???

  def host(): String = ???

  def port(): Int = ???

  def path(path: String = null): String = ???

  // TODO: refine argument types?
  def search(search: js.Any, paramValue: js.Any = null) : js.Object = ???

  def hash(hash: String = null): String = ???

  def replace() : Unit = ???
}

object Location {

  val Name = "$location"
}
