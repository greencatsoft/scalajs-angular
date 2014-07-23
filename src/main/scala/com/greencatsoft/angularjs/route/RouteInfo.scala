package com.greencatsoft.angularjs.route

import scala.scalajs.js

import com.greencatsoft.angularjs.scope.Scope

trait RouteInfo extends js.Object {

  var $$route: Route

  var loadedTemplateUrl: String

  var params: js.Array[js.Any]

  var pathParams: js.Array[js.Any]

  var scope: Scope
}
