package com.greencatsoft.angularjs.dom

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait DocumentAware extends InjectionTarget {

  implicit var document: org.scalajs.dom.Document = _

  override def dependencies = super.dependencies :+ Document.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Document.Name) ensuring (_ >= 0)
    this.document = args(index).asInstanceOf[Document]
  }
}
