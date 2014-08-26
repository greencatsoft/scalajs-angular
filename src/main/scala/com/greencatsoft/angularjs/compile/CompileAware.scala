package com.greencatsoft.angularjs.compile

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait CompileAware extends InjectionTarget {

  implicit var compile: Compile = _

  override def dependencies = super.dependencies :+ Compile.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Compile.Name) ensuring (_ >= 0)
    this.compile = args(index).asInstanceOf[Compile]
  }
}
