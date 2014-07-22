package com.greencatsoft.angularjs.fileupload

import scala.scalajs.js

import org.scalajs.dom.File

trait FileUploadConfig extends js.Object {

  var url: String = _

  var data: Any = _

  var file: File = _

  var method: String = _

  var fileName: js.Any = _

  var fileFormDataName: js.Any = _
}

object FileUploadConfig {

  def apply(url: String, file: File, data: Option[Any] = None) = {
    require(url != null, "Missing argument 'url'.")
    require(file != null, "Missing argument 'file'.")

    val config = new js.Object().asInstanceOf[FileUploadConfig]

    config.url = url
    config.file = file

    data.foreach(config.data = _)

    config
  }
}
