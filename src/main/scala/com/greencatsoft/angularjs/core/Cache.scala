package com.greencatsoft.angularjs.core

import scala.scalajs.js
import scala.scalajs.js.UndefOr

import com.greencatsoft.angularjs.injectable

@injectable("$cache")
trait Cache extends js.Object {

  def info(): js.Dictionary[js.Any] = js.native

  def put[T <: js.Any](key: String, value: T): T = js.native

  def get[T <: js.Any](key: String): UndefOr[T] = js.native

  def remove(key: String): Unit = js.native

  def removeAll(): Unit = js.native

  def destroy(): Unit = js.native
}

@injectable("$cacheFactory")
trait CacheFactory extends js.Object {

  def apply(cacheId: String, options: js.Object): Cache = js.native
}
