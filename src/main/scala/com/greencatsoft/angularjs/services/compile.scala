package com.greencatsoft.angularjs.services

import com.greencatsoft.angularjs.InjectionTarget

import scala.scalajs.js

trait Compile extends js.Function3[js.Any,js.Function,Int,js.Function] {

}

object Compile {
  val Name = "$compile"
}

trait CompileAware extends InjectionTarget {
  implicit var compile: Compile = _

  override def dependencies = super.dependencies :+ Compile.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Compile.Name) ensuring (_ >= 0)
    this.compile = args(index).asInstanceOf[Compile]
  }
}