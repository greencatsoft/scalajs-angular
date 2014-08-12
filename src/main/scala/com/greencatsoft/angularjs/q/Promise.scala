package com.greencatsoft.angularjs.q

import scala.scalajs.js

trait Promise extends js.Object {

  def then(successCallback: js.Function1[js.Any, Unit], errorCallback: js.Function1[js.Any, Unit], notifyCallback: js.Function1[js.Any, Unit]): this.type = ???

  def `catch`(errorCallback: js.Function1[js.Any, Unit]): this.type = ???

  def `finally`(callback: js.Function1[js.Any, Unit]): Unit = ???
}
