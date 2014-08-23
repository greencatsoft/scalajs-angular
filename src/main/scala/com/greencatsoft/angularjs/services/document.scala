package com.greencatsoft.angularjs.services

import com.greencatsoft.angularjs.InjectionTarget

import scala.scalajs.js

object Document {
 val Name = "$document"
}


trait DocumentAware extends InjectionTarget {
  implicit var document: org.scalajs.dom.Document = _

  override def dependencies = super.dependencies :+ Document.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(Document.Name) ensuring (_ >= 0)
    this.document = args(index).asInstanceOf[org.scalajs.dom.Document]
  }
}