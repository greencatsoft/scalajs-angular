package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

@js.native
@injectable("$parse")
trait Parse extends js.Object {

  type ParsedExpression = js.Function2[js.Object, js.Object, js.Function]

  def apply(expression: String): ParsedExpression = js.native
}
