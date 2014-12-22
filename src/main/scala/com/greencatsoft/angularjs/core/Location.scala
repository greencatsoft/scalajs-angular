package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.injectable

@injectable("$location")
trait Location extends js.Object {

  def absUrl(): String = js.native

  def url(url: String = null, replace: String = null): String = js.native

  def protocol(): String = js.native

  def host(): String = js.native

  def port(): Int = js.native

  def path(): String = js.native

  def path(path: String): Location = js.native

  // TODO: refine argument types?
  def search(search: js.Any, paramValue: js.Any = null): js.Object = js.native

  def hash(hash: String = null): String = js.native

  def replace(): Unit = js.native
}
