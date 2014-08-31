package com.greencatsoft.angularjs.directive

import scala.scalajs.js

import com.greencatsoft.angularjs.{ ConfigBuilder, NamedTarget }

trait Requires extends ConfigBuilder {
  this: Directive =>

  val requirements = Set.empty[NamedTarget]

  abstract override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("require") = js.Array[String](requirements.toSeq.map("^" + _.name): _*)

    super.buildConfig(config)
  }
}
