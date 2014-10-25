package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.injectable

@injectable("$location")
trait Location extends js.Object {

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
