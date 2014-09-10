package com.greencatsoft.angularjs.http

import scala.scalajs.js

trait HttpService extends js.Object {

  def get(url: String): HttpPromise = ???

  def get(url: String, config: HttpConfig): HttpPromise = ???

  def post(url: String): HttpPromise = ???

  def post(url: String, data: js.Any): HttpPromise = ???

  def post(url: String, data: js.Any, config: HttpConfig): HttpPromise = ???

  def put(url: String): HttpPromise = ???

  def put(url: String, data: js.Any): HttpPromise = ???

  def put(url: String, data: js.Any, config: HttpConfig): HttpPromise = ???

  def delete(url: String): HttpPromise = ???

  def delete(url: String, data: js.Any): HttpPromise = ???

  def delete(url: String, data: js.Any, config: HttpConfig): HttpPromise = ???
}

object HttpService {

  val Name = "$http"
}
