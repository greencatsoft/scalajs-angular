package com.greencatsoft.angularjs.anchorScroll

import com.greencatsoft.angularjs.InjectionTarget

import scala.scalajs.js

trait AnchorScrollAware extends InjectionTarget {
  implicit var anchorScroll: AnchorScroll = _

  override def dependencies = super.dependencies :+ AnchorScroll.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(AnchorScroll.Name) ensuring (_ >= 0)
    this.anchorScroll = args(index).asInstanceOf[AnchorScroll]
  }
}
