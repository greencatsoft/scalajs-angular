package com.greencatsoft.angularjs.i18next

import com.greencatsoft.angularjs.InjectionTarget

import scala.scalajs.js

trait I18nextProviderAware extends InjectionTarget {

  var provider: I18nextProvider = _

  override def dependencies = super.dependencies :+ I18nextProvider.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(I18nextProvider.Name) ensuring (_ >= 0)
    this.provider = args(index).asInstanceOf[I18nextProvider]
  }
}
