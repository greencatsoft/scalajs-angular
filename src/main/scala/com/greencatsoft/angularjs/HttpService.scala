package com.greencatsoft.angularjs

import scala.scalajs.js

trait HttpService extends js.Object {

  def get(url: String): HttpPromise = ???

  def get(url: String, config: HttpConfig): HttpPromise = ???

  def post(url: String, data: js.Any): HttpPromise = ???

  def post(url: String, data: js.Any, config: HttpConfig): HttpPromise = ???
}

object HttpService {

  val Name = "$http"
}
