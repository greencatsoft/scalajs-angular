package com.greencatsoft.angularjs.animate

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait AnimateAware extends InjectionTarget {

  implicit var animate: Animate = _

  override def dependencies = super.dependencies :+ Animate.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Animate.Name) ensuring (_ >= 0)
    this.animate = args(index).asInstanceOf[Animate]
  }
}
