package com.greencatsoft.angularjs.core

import scala.concurrent.Future
import scala.language.implicitConversions
import scala.scalajs.js

import com.greencatsoft.angularjs.core.Promise.promise2future
import com.greencatsoft.angularjs.injectable

trait EnhancedResource[T] extends js.Object {

  import Resource._

  def get(): ResourceResponse[T] = js.native

  def get(success: SuccessHandler): ResourceResponse[T] = js.native

  def get(success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def get(parameters: js.Dictionary[Any]): ResourceResponse[T] = js.native

  def get(parameters: js.Dictionary[Any], success: SuccessHandler): ResourceResponse[T] = js.native

  def get(parameters: js.Dictionary[Any], success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def save(postData: T): ResourceResponse[T] = js.native

  def save(postData: T, success: SuccessHandler): ResourceResponse[T] = js.native

  def save(postData: T, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def save(parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse[T] = js.native

  def save(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def save(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def query(): ResourceResponse[js.Array[T]] = js.native

  def query(success: SuccessHandler): ResourceResponse[js.Array[T]] = js.native

  def query(success: SuccessHandler, error: ErrorHandler): ResourceResponse[js.Array[T]] = js.native

  def query(parameters: js.Dictionary[Any]): ResourceResponse[js.Array[T]] = js.native

  def query(parameters: js.Dictionary[Any], success: SuccessHandler): ResourceResponse[js.Array[T]] = js.native

  def query(parameters: js.Dictionary[Any], success: SuccessHandler, error: ErrorHandler): ResourceResponse[js.Array[T]] = js.native

  def remove(postData: js.Any): ResourceResponse[T] = js.native

  def remove(postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def remove(postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def remove(parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse[T] = js.native

  def remove(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def remove(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def delete(postData: js.Any): ResourceResponse[T] = js.native

  def delete(postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def delete(postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def delete(parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse[T] = js.native

  def delete(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def delete(parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native
}

trait Resource extends js.Object {

  import Resource._

  def get[T](): ResourceResponse[T] = js.native

  def get[T](success: SuccessHandler): ResourceResponse[T] = js.native

  def get[T](success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def get[T](parameters: js.Dictionary[Any]): ResourceResponse[T] = js.native

  def get[T](parameters: js.Dictionary[Any], success: SuccessHandler): ResourceResponse[T] = js.native

  def get[T](parameters: js.Dictionary[Any], success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def save[T](postData: js.Any): ResourceResponse[T] = js.native

  def save[T](postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def save[T](postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def save[T](parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse[T] = js.native

  def save[T](parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def save[T](parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def query[T](): ResourceResponse[T] = js.native

  def query[T](success: SuccessHandler): ResourceResponse[T] = js.native

  def query[T](success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def query[T](parameters: js.Dictionary[Any]): ResourceResponse[T] = js.native

  def query[T](parameters: js.Dictionary[Any], success: SuccessHandler): ResourceResponse[T] = js.native

  def query[T](parameters: js.Dictionary[Any], success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def remove[T](postData: js.Any): ResourceResponse[T] = js.native

  def remove[T](postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def remove[T](postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def remove[T](parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse[T] = js.native

  def remove[T](parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def remove[T](parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def delete[T](postData: js.Any): ResourceResponse[T] = js.native

  def delete[T](postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def delete[T](postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native

  def delete[T](parameters: js.Dictionary[Any], postData: js.Any): ResourceResponse[T] = js.native

  def delete[T](parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler): ResourceResponse[T] = js.native

  def delete[T](parameters: js.Dictionary[Any], postData: js.Any, success: SuccessHandler, error: ErrorHandler): ResourceResponse[T] = js.native
}

object Resource {

  type SuccessHandler = js.Function2[js.Any, js.Any, Unit]

  type ErrorHandler = js.Function1[js.Any, Unit]
}

trait ResourceResponse[T] extends js.Object {

  def $promise: Promise[T] = js.native

  def $resolved: Boolean = js.native
}

object ResourceResponse {

  implicit def promise2future[A](json: ResourceResponse[A]): Future[A] = json.$promise
}

@injectable("$resource")
trait ResourceService extends js.Object {

  def apply(url: String): Any = js.native

  def apply(url: String, paramDefaults: js.Dictionary[js.Any]): Any = js.native

  def apply(url: String, paramDefaults: js.Dictionary[js.Any], actions: js.Dictionary[ResourceRequestConfig]): Any = js.native
}

trait ResourceRequestConfig extends HttpConfig {

  var isArray: Boolean = js.native
}

object ResourceRequestConfig {

  def apply(
    isArray: Boolean = false,
    params: js.Dictionary[js.Any] = null,
    method: String = "GET",
    url: String = null,
    timeout: Int = -1): ResourceRequestConfig = {

    val t = js.Object().asInstanceOf[ResourceRequestConfig]

    if (!isArray) t.isArray = isArray
    if (params != null) t.params = params
    if (method != null) t.method = method
    if (url != null) t.url = url

    t
  }
}
