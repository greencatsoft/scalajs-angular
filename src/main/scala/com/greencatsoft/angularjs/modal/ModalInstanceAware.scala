package com.greencatsoft.angularjs.modal

import com.greencatsoft.angularjs.InjectionTarget

import scala.scalajs.js

trait ModalInstanceAware extends InjectionTarget {

  var modalInstance: ModalInstance = _

  override def dependencies = super.dependencies :+ ModalInstance.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    val index = dependencies.indexOf(ModalInstance.Name) ensuring (_ >= 0)
    this.modalInstance = args(index).asInstanceOf[ModalInstance]
  }
}
