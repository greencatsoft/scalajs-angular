package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js
import scala.scalajs.js.UndefOr

@js.native
@injectable("$exceptionHandler")
trait ExceptionHandler extends js.Function2[js.Error, UndefOr[String], Unit] {

  override def apply(exception: js.Error, cause: UndefOr[String]): Unit = js.native
}
