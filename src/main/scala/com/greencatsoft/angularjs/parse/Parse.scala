package com.greencatsoft.angularjs.parse

import scala.scalajs.js

trait Parse extends js.Function1[String,js.Function]

object Parse {
  val Name = "$parse"
}
