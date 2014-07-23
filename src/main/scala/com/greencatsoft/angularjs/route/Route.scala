package com.greencatsoft.angularjs.route

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.any2undefOrA

import com.greencatsoft.angularjs.NamedTarget

trait Route extends js.Object {

  var title: UndefOr[String]

  var templateUrl: String

  var controller: UndefOr[String]

  var redirectTo: UndefOr[String]
}

object Route {

  def apply(controller: TemplateController): Route = {
    require(controller != null, "Missing argument 'controller'.")
    apply(controller.templateUrl, controller.title, Some(controller))
  }

  def apply(templateUrl: String, title: Option[String], controller: Option[NamedTarget]): Route = {
    require(templateUrl != null, "Missing argument 'templateUrl'.")
    require(title != null, "Missing argument 'title'.")
    require(controller != null, "Missing argument 'controller'.")

    var route = new js.Object().asInstanceOf[Route]

    route.templateUrl = templateUrl

    title.foreach(t => route.title = t)
    controller.foreach(c => route.controller = c.name)

    route
  }

  def apply(redirectTo: String) = {
    require(redirectTo != null, "Missing argument 'redirectTo'.")

    var route = new js.Object().asInstanceOf[Route]
    route.redirectTo = redirectTo

    route
  }
}