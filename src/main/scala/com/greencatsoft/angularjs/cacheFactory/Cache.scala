package com.greencatsoft.angularjs.cacheFactory

import scala.scalajs.js

// TODO: should add a type to Cache so we can retrieve typed objects from it?
trait Cache extends js.Object {
  def info() : js.Dictionary[js.Any] = ???
  def put(key: String, value: js.Object) : js.Object = ???
  def get(key: String) : js.Object = ???
  def remove(key: String) : Unit = ???
  def removeAll() : Unit = ???
  def destroy() : Unit = ???
}
