package com.greencatsoft.angularjs.services

import com.greencatsoft.angularjs.InjectionTarget
import com.greencatsoft.angularjs.q.Promise

import scala.scalajs.js

trait Timeout extends js.Function3[js.Function,Int,Boolean,Promise] {
  def cancel(promise: Promise = null) : Boolean= ???
}

object Timeout {
  val Name = "$timeout"
}

trait TimeoutAware extends InjectionTarget {
  implicit var timeout: Timeout = _

  override def dependencies = super.dependencies :+ Timeout.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Timeout.Name) ensuring (_ >= 0)
    this.timeout = args(index).asInstanceOf[Timeout]
  }
}
