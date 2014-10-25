package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.injectable

@injectable("$parse")
trait Parse extends js.Object {

  type ParsedExpression = js.Function2[js.Object, js.Object, js.Function]

  def apply(expression: String): ParsedExpression = ???
}
