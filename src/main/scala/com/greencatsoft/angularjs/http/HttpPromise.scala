package com.greencatsoft.angularjs.http

import scala.scalajs.js
import scala.scalajs.js.UndefOr

import com.greencatsoft.angularjs.q.Promise

trait HttpPromise extends Promise {

  def success(callback: js.Function1[js.Any, Unit]): this.type

  def success(callback: js.Function2[js.Any, Int, Unit]): this.type

  def success(callback: js.Function3[js.Any, js.Any, Int, Unit]): this.type

  def success(callback: js.Function4[js.Any, Int, js.Any, js.Any, Unit]): this.type

  def success(callback: js.Function5[js.Any, Int, js.Any, js.Any, js.Any, Unit]): this.type

  def error(callback: js.Function1[js.Any, Unit]): this.type

  def error(callback: js.Function2[js.Any, Int, Unit]): this.type

  def error(callback: js.Function3[js.Any, js.Any, Int, Unit]): this.type

  def error(callback: js.Function4[js.Any, Int, js.Any, js.Any, Unit]): this.type

  def error(callback: js.Function5[js.Any, Int, js.Any, js.Any, UndefOr[String], Unit]): this.type
}
