package com.greencatsoft.angularjs.services

import com.greencatsoft.angularjs.InjectionTarget
import com.greencatsoft.angularjs.q.Promise

import scala.scalajs.js


trait Interval extends js.Function4[js.Function,Int,Int,Boolean,Promise] {
  def cancel(promise: Promise = null) : Boolean= ???
}

object Interval {
  val Name = "$interval"
}

trait IntervalAware extends InjectionTarget {
  implicit var interval: Interval = _

  override def dependencies = super.dependencies :+ Interval.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Interval.Name) ensuring (_ >= 0)
    this.interval = args(index).asInstanceOf[Interval]
  }
}