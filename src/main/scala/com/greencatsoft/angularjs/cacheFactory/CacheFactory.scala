package com.greencatsoft.angularjs.cacheFactory

import scala.scalajs.js

trait CacheFactory extends js.Function2[String,js.Object,Cache]

object CacheFactory {
  val Name = "$cacheFactory"
}