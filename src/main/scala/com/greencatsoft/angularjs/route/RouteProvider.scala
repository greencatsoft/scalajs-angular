package com.greencatsoft.angularjs.route

import scala.scalajs.js

trait RouteProvider extends js.Object {

  def when(path: String, route: Route): this.type = ???

  def otherwise(route: Route): this.type = ???
}

object RouteProvider {

  val Name = "$routeProvider"
}
