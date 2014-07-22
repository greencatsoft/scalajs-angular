package com.greencatsoft.angularjs.fileupload

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait FileUploadAware extends InjectionTarget {

  implicit var upload: FileUpload = _

  override def dependencies = super.dependencies :+ FileUpload.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)

    var index = dependencies.indexOf(FileUpload.Name) ensuring (_ >= 0)
    this.upload = args(index).asInstanceOf[FileUpload]
  }
}
