package com.greencatsoft.angularjs.timer

import scala.scalajs.js
import com.greencatsoft.angularjs.InjectionTarget

trait IntervalAware extends InjectionTarget {

  implicit var interval: Interval = _

  override def dependencies = super.dependencies :+ Interval.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Interval.Name) ensuring (_ >= 0)
    this.interval = args(index).asInstanceOf[Interval]
  }
}
