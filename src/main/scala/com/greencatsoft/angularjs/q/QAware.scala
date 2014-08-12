package com.greencatsoft.angularjs.q

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait QAware extends InjectionTarget {

  var q: Q = _

  override def dependencies = super.dependencies :+ Q.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Q.Name) ensuring (_ >= 0)
    this.q = args(index).asInstanceOf[Q]
  }
}
