package com.greencatsoft.angularjs.timer

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait TimeoutAware extends InjectionTarget {

  implicit var timeout: Timeout = _

  override def dependencies = super.dependencies :+ Timeout.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Timeout.Name) ensuring (_ >= 0)
    this.timeout = args(index).asInstanceOf[Timeout]
  }
}
