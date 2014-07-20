package com.greencatsoft.angularjs

import scala.scalajs.js

trait HttpServiceAware extends InjectionTarget {

  implicit var http: HttpService = _

  override def dependencies = super.dependencies :+ HttpService.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(HttpService.Name) ensuring (_ >= 0)
    this.http = args(index).asInstanceOf[HttpService]
  }
}