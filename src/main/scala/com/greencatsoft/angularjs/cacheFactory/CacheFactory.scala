package com.greencatsoft.angularjs.cacheFactory

import scala.scalajs.js
import com.greencatsoft.angularjs.Injectable

trait CacheFactory extends Injectable {

  def apply(cacheId: String, options: js.Object): Cache = ???
}

object CacheFactory {

  val Name = "$cacheFactory"
}