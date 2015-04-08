package com.greencatsoft.angularjs.core

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.any2undefOrA

import com.greencatsoft.angularjs.injectable

@injectable("$routeProvider")
trait RouteProvider extends js.Object {

  def when(path: String, route: Route): this.type = js.native

  def otherwise(route: Route): this.type = js.native
}

trait Route extends js.Object {

  var title: UndefOr[String] = js.native

  var templateUrl: UndefOr[String] = js.native

  var controller: UndefOr[String] = js.native

  var redirectTo: UndefOr[String] = js.native

  var resolve: js.Dictionary[js.Any] = js.native
}

object Route {

  def apply(templateUrl: String): Route =
    apply(templateUrl, None, None, None)

  def apply(templateUrl: String, title: String): Route =
    apply(templateUrl, Some(title), None, None)

  def apply(templateUrl: String, title: String, controller: String): Route =
    apply(templateUrl, Some(title), Some(controller), None)

  def apply(
    templateUrl: String, title: Option[String], controller: Option[String], redirectTo: Option[String]): Route = {
    require(templateUrl != null, "Missing argument 'templateUrl'.")
    require(title != null, "Missing argument 'title'.")
    require(controller != null, "Missing argument 'controller'.")
    require(redirectTo != null, "Missing argument 'redirectTo'.")

    val route = new js.Object().asInstanceOf[Route]

    route.templateUrl = templateUrl
    route.resolve = js.Dictionary.empty

    title.foreach(route.title = _)
    controller.foreach(route.controller = _)
    redirectTo.foreach(route.redirectTo = _)

    route
  }

  def redirectTo(url: String): Route = {
    require(url != null, "Missing argument 'url'.")

    val route = new js.Object().asInstanceOf[Route]

    route.redirectTo = url
    route
  }
}

trait RouteInfo extends js.Object {

  var $$route: Route = js.native

  var loadedTemplateUrl: String = js.native

  var params: js.Array[js.Any] = js.native

  var pathParams: js.Array[js.Any] = js.native

  var scope: Scope = js.native
}
