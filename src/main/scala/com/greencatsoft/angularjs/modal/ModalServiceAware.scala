package com.greencatsoft.angularjs.modal

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait ModalServiceAware extends InjectionTarget {

  implicit var modal: ModalService = _

  override def dependencies = super.dependencies :+ ModalService.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(ModalService.Name) ensuring (_ >= 0)
    this.modal = args(index).asInstanceOf[ModalService]
  }
}