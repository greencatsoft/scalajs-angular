package com.greencatsoft.angularjs

import scala.scalajs.js

trait ModalOptions extends js.Object {

  var templateUrl: String = _

  var controller: String = _

  var scope: Any = _

  var size: String = _

  var windowClass: String = _

  var backdrop = true

  var keyboard = true
}

object ModalOptions {

  def apply() = {
    val options = new js.Object().asInstanceOf[ModalOptions]

    options.size = "lg"

    options
  }
}