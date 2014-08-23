package com.greencatsoft.angularjs.services

import com.greencatsoft.angularjs.InjectionTarget

import scala.scalajs.js

trait CacheFactory extends js.Function2[String,js.Object,Cache]

object CacheFactory {
  val Name = "$cacheFactory"
}

// TODO: should add a type to Cache so we can retrieve typed objects from it?
trait Cache extends js.Object {
  def info() : js.Dictionary[js.Any] = ???
  def put(key: String, value: js.Object) : js.Object = ???
  def get(key: String) : js.Object = ???
  def remove(key: String) : Unit = ???
  def removeAll() : Unit = ???
  def destroy() : Unit = ???
}

trait CacheFactoryAware extends InjectionTarget {
  implicit var cacheFactory: CacheFactory = _

  override def dependencies = super.dependencies :+ CacheFactory.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(CacheFactory.Name) ensuring (_ >= 0)
    this.cacheFactory = args(index).asInstanceOf[CacheFactory]
  }
}