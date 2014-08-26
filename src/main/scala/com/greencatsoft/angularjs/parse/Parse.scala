package com.greencatsoft.angularjs.parse

import scala.scalajs.js
import com.greencatsoft.angularjs.Injectable

trait Parse extends Injectable {

  type ParsedExpression = js.Function2[js.Object, js.Object, js.Function]

  def apply(expression: String): ParsedExpression = ???
}

object Parse {

  val Name = "$parse"
}
