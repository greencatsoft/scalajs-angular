package com.greencatsoft.angularjs.cacheFactory

import scala.scalajs.js
import scala.scalajs.js.UndefOr

import com.greencatsoft.angularjs.Injectable

trait Cache extends Injectable {

  def info(): js.Dictionary[js.Any] = ???

  def put[T <: js.Any](key: String, value: T): T = ???

  def get[T <: js.Any](key: String): UndefOr[T] = ???

  def remove(key: String): Unit = ???

  def removeAll(): Unit = ???

  def destroy(): Unit = ???
}
