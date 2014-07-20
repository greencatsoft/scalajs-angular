package com.greencatsoft.angularjs

import scala.scalajs.js

trait HttpProviderAware extends InjectionTarget {

  implicit var httpProvider: HttpProvider = _

  override def dependencies = super.dependencies :+ HttpProvider.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(HttpProvider.Name) ensuring (_ >= 0)
    this.httpProvider = args(index).asInstanceOf[HttpProvider]
  }
}