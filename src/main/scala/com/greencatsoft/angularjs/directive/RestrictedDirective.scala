package com.greencatsoft.angularjs.directive

import scala.scalajs.js
import scala.scalajs.js.Any.fromString

import com.greencatsoft.angularjs.ConfigBuilder

trait RestrictedDirective extends Directive with ConfigBuilder {

  def restrict: Set[String] = Set.empty

  abstract override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("restrict") = restrict.mkString

    super.buildConfig(config)
  }
}

trait ElementDirective extends RestrictedDirective {

  override def restrict = super.restrict + "E"

  var transclude = false

  var replace = false

  abstract override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("transclude") = transclude
    config("replace") = replace

    super.buildConfig(config)
  }
}

trait AttributeDirective extends RestrictedDirective {

  override def restrict = super.restrict + "A"
}

trait ClassDirective extends RestrictedDirective {

  override def restrict = super.restrict + "C"
}

trait CommentDirective extends RestrictedDirective {

  override def restrict = super.restrict + "M"
}
