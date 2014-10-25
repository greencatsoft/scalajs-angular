package com.greencatsoft.angularjs.core

import scala.scalajs.js
import scala.scalajs.js.UndefOr

import com.greencatsoft.angularjs.injectable

@injectable("$cache")
trait Cache extends js.Object {

  def info(): js.Dictionary[js.Any] = ???

  def put[T <: js.Any](key: String, value: T): T = ???

  def get[T <: js.Any](key: String): UndefOr[T] = ???

  def remove(key: String): Unit = ???

  def removeAll(): Unit = ???

  def destroy(): Unit = ???
}

@injectable("$cacheFactory")
trait CacheFactory extends js.Object {

  def apply(cacheId: String, options: js.Object): Cache = ???
}
