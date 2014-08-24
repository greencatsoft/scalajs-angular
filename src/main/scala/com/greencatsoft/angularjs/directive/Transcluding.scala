package com.greencatsoft.angularjs.directive

import scala.scalajs.js
import scala.scalajs.js.Any.fromBoolean

import com.greencatsoft.angularjs.ConfigBuilder

trait Transcluding extends ConfigBuilder {
  this: Directive =>

  abstract override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("transclude") = true

    super.buildConfig(config)
  }
}
