package com.greencatsoft.angularjs.extensions

import scala.language.implicitConversions
import scala.scalajs.js
import com.greencatsoft.angularjs.{ injectable}
import com.greencatsoft.angularjs.core.{ HttpConfig }
import scala.concurrent.Future
import com.greencatsoft.angularjs.core.Promise

trait Resource extends js.Object {
  def get(): JsonResult = js.native
  def get(parameters: js.Dictionary[Any]): JsonResult = js.native
  def save(data: js.Any): JsonResult = js.native
  def save(parameters: js.Dictionary[Any], data: js.Any): JsonResult = js.native
  def query(): JsonResult = js.native
  def query(parameters: js.Dictionary[Any]): JsonResult = js.native
  def remove(data: js.Any): JsonResult = js.native
  def remove(parameters: js.Dictionary[Any], data: js.Any): JsonResult = js.native
  def delete(data: js.Any): JsonResult = js.native
  def delete(parameters: js.Dictionary[Any], data: js.Any): JsonResult = js.native
}

trait JsonResult extends js.Object {
  var $promise: Promise = js.native
}

object JsonResult {
  implicit def promise2future[A](json: JsonResult): Future[A] = json.$promise
}

@injectable("$resource")
trait ResourceService extends js.Object {
  def apply(url: String): Any = js.native
  def apply(url: String, paramDefaults: js.Dictionary[Any]): Any = js.native
  def apply(url: String, paramDefaults: js.Dictionary[Any], actions: js.Dictionary[JsonConfig]): Any = js.native
}
trait JsonConfig extends HttpConfig {
  var isArray: Boolean = js.native
  var params: js.Dictionary[Any] = js.native
  var method: String = js.native
  var url: String = js.native
  var timeout: Int = js.native
  var withCredentials: Boolean = js.native
}

object JsonConfig {
  def apply(isArray: Boolean = false, params: js.Dictionary[Any] = null, method: String = "GET", url: String = null, timeout: Int = -1): JsonConfig = {
    val t = js.Dynamic.literal().asInstanceOf[JsonConfig]
    if (isArray != false)
      t.isArray = isArray
    if (params != null)
      t.params = params
    if (method != null)
      t.method = method
    if (url != null)
      t.url = url
    t
  }
}