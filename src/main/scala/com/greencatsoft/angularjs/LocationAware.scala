package com.greencatsoft.angularjs

import scala.scalajs.js

trait LocationAware extends InjectionTarget {

  implicit var location: Location = _

  override def dependencies = super.dependencies :+ Location.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Location.Name) ensuring (_ >= 0)
    this.location = args(index).asInstanceOf[Location]
  }
}