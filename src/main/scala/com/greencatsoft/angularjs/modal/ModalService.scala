package com.greencatsoft.angularjs.modal

import scala.scalajs.js

trait ModalService extends js.Object {

  def open(options: ModalOptions): ModalInstance = ???
}

object ModalService {

  val Name = "$modal"
}