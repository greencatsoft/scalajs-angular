package com.greencatsoft.angularjs.core

import scala.concurrent.Future
import scala.language.implicitConversions
import scala.scalajs.js

import com.greencatsoft.angularjs.core.Promise.promise2future
import com.greencatsoft.angularjs.injectable

trait Resource extends js.Object {

  import Resource._

  def get(): ResourceResponse = js.native

  def get(success: SuccessHandler): ResourceResponse = js.native

  def get(success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def get(parameters: js.Dictionary[Any]): ResourceResponse = js.native

  def get(parameters: js.Dictionary[Any], success: SuccessHandler): ResourceResponse = js.native

  def get(parameters: js.Dictionary[Any], success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def save(postData: js.Any): ResourceResponse = js.native

  def save(postData: js.Any, success: SuccessHandler): ResourceResponse = js.native

  def save(postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def save(parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse = js.native

  def save(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse = js.native

  def save(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def query(): ResourceResponse = js.native

  def query(success: SuccessHandler): ResourceResponse = js.native

  def query(success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def query(parameters: js.Dictionary[Any]): ResourceResponse = js.native

  def query(parameters: js.Dictionary[Any], success: SuccessHandler): ResourceResponse = js.native

  def query(parameters: js.Dictionary[Any], success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def remove(postData: js.Any): ResourceResponse = js.native

  def remove(postData: js.Any, success: SuccessHandler): ResourceResponse = js.native

  def remove(postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def remove(parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse = js.native

  def remove(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse = js.native

  def remove(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def delete(postData: js.Any): ResourceResponse = js.native

  def delete(postData: js.Any, success: SuccessHandler): ResourceResponse = js.native

  def delete(postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native

  def delete(parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse = js.native

  def delete(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse = js.native

  def delete(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse = js.native
}

object Resource {

  type SuccessHandler = js.Function2[js.Any, js.Any, Unit]

  type ErrorHandler = js.Function1[js.Any, Unit]
}

trait ResourceResponse extends js.Object {

  def $promise: Promise = js.native

  def $resolved: Boolean = js.native
}

object ResourceResponse {

  implicit def promise2future[A](json: ResourceResponse): Future[A] = json.$promise
}

@injectable("$resource")
trait ResourceService extends js.Object {

  def apply(url: String): Any = js.native

  def apply(url: String, paramDefaults: js.Dictionary[Any]): Any = js.native

  def apply(url: String, paramDefaults: js.Dictionary[Any], actions: js.Dictionary[ResourceRequestConfig]): Any = js.native
}

trait ResourceRequestConfig extends HttpConfig {

  var isArray: Boolean = js.native
}

object ResourceRequestConfig {

  def apply(
    isArray: Boolean = false,
    params: js.Dictionary[Any] = null,
    method: String = "GET",
    url: String = null,
    timeout: Int = -1): ResourceRequestConfig = {

    val t = js.Dynamic.literal.asInstanceOf[ResourceRequestConfig]

    if (!isArray) t.isArray = isArray
    if (params != null) t.params = params
    if (method != null) t.method = method
    if (url != null) t.url = url

    t
  }
}