package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

@injectable("$injector")
@js.native
trait Injector extends js.Object {
  def get(name: String): js.Object = js.native
}
