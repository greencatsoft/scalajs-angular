package com.greencatsoft.angularjs.compile

import scala.scalajs.js

trait Compile extends js.Function3[js.Any,js.Function,Int,js.Function]

object Compile {
  val Name = "$compile"
}
