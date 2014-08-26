package com.greencatsoft.angularjs.location

import scala.scalajs.js

import com.greencatsoft.angularjs.Injectable

trait Location extends Injectable {

  def absUrl(): String = ???

  def url(url: String = null, replace: String = null): String = ???

  def protocol(): String = ???

  def host(): String = ???

  def port(): Int = ???

  def path(path: String = null): String = ???

  // TODO: refine argument types?
  def search(search: js.Any, paramValue: js.Any = null): js.Object = ???

  def hash(hash: String = null): String = ???

  def replace(): Unit = ???
}

object Location {

  val Name = "$location"
}
