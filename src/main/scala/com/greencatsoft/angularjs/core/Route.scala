package com.greencatsoft.angularjs.core

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSBracketAccess

import com.greencatsoft.angularjs.injectable

@js.native
@injectable("$routeParams")
trait RouteParams extends js.Object {

  @JSBracketAccess
  def apply(key: String): js.Any = js.native

  @JSBracketAccess
  def get(key: String): UndefOr[js.Any] = js.native
}

@js.native
@injectable("$routeProvider")
trait RouteProvider extends js.Object {

  def when(path: String, route: Route): this.type = js.native

  def otherwise(route: Route): this.type = js.native
}

@js.native
trait Route extends js.Object {

  var title: UndefOr[String] = js.native

  var template: UndefOr[String] = js.native

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

    val route = js.Object().asInstanceOf[Route]

    route.redirectTo = url
    route
  }
}

class RouteBuilder {

  private val route: Route = js.Object().asInstanceOf[Route]

  route.resolve = js.Dictionary.empty

  def build: Route = route

  def title(title: String): RouteBuilder = {
    route.title = title
    this
  }

  def template(template: String): RouteBuilder = {
    route.template = template
    this
  }

  def templateUrl(templateUrl: String): RouteBuilder = {
    route.templateUrl = templateUrl
    this
  }

  def controller(controller: String): RouteBuilder = {
    route.controller = controller
    this
  }

  def redirectTo(redirectTo: String): RouteBuilder = {
    route.redirectTo = redirectTo
    this
  }

  def resolve(dependencies: js.Dictionary[js.Any]): RouteBuilder = {
    route.resolve = dependencies
    this
  }
}

object RouteBuilder {

  def apply(): RouteBuilder = new RouteBuilder
}

@js.native
trait RouteInfo extends js.Object {

  var $$route: Route = js.native

  var loadedTemplateUrl: String = js.native

  var params: js.Array[js.Any] = js.native

  var pathParams: js.Array[js.Any] = js.native

  var scope: Scope = js.native
}
