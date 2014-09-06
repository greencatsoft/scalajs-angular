package com.greencatsoft.angularjs.cachefactory

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait CacheFactoryAware extends InjectionTarget {

  implicit var cacheFactory: CacheFactory = _

  override def dependencies = super.dependencies :+ CacheFactory.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(CacheFactory.Name) ensuring (_ >= 0)
    this.cacheFactory = args(index).asInstanceOf[CacheFactory]
  }
}
