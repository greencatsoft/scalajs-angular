package com.greencatsoft.angularjs.services

import com.greencatsoft.angularjs.InjectionTarget

import scala.scalajs.js

trait AnchorScroll extends js.Function0[Unit]

object AnchorScroll {
  val Name = "$anchorScroll"
}

trait AnchorScrollAware extends InjectionTarget {
  implicit var anchorScroll: AnchorScroll = _

  override def dependencies = super.dependencies :+ AnchorScroll.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(AnchorScroll.Name) ensuring (_ >= 0)
    this.anchorScroll = args(index).asInstanceOf[AnchorScroll]
  }
}