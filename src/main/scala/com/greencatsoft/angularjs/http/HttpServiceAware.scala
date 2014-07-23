package com.greencatsoft.angularjs.http

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait HttpServiceAware extends InjectionTarget {

  implicit var http: HttpService = _

  override def dependencies = super.dependencies :+ HttpService.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(HttpService.Name) ensuring (_ >= 0)
    this.http = args(index).asInstanceOf[HttpService]
  }
}