package com.greencatsoft.angularjs.core

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.{ UndefOr, | }

import com.greencatsoft.angularjs.injectable

@js.native
@injectable("$cookies")
trait CookieService extends js.Object {

  def defaults: HttpConfig = js.native

  def get(key: String): UndefOr[String] = js.native

  def getObject(key: String): UndefOr[js.Object] = js.native

  def getAll(): js.Dictionary[String] = js.native

  def put(key: String, value: String): Unit = js.native

  def put(key: String, value: String, options: CookiesConfig): Unit = js.native

  def putObject(key: String, value: js.Object): Unit = js.native

  def putObject(key: String, value: js.Object, options: CookiesConfig): Unit = js.native

  def remove(key: String): Unit = js.native

  def remove(key: String, options: CookiesConfig): Unit = js.native
}

@js.native
@injectable("$cookiesProvider")
trait CookiesProvider extends js.Object {

  var defaults: CookiesConfig = js.native
}

@js.native
trait CookiesConfig extends js.Object {

  var path: String = js.native

  var domain: String = js.native

  var expires: String | js.Date = js.native

  var secure: Boolean = js.native
}
