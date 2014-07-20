package com.greencatsoft.angularjs

import scala.scalajs.js

trait HttpConfig extends js.Object {

  val cache = false

  var responseType = ""

  var headers = new js.Array[js.Any]

  var transformResponse: js.Array[js.Function2[js.Any, js.Any, js.Any]] = _

  var transformRequest: js.Array[js.Function2[js.Any, js.Any, js.Any]] = _
}

object HttpConfig {

  def apply() = {
    val config = new js.Object().asInstanceOf[HttpConfig]

    config.transformRequest = js.Array()
    config.transformResponse = js.Array()

    config
  }

  def documentHandler(): HttpConfig = {
    val config = apply()

    config.responseType = "document"

    config
  }
}