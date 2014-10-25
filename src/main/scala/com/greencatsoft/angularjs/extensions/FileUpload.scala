package com.greencatsoft.angularjs.extensions

import scala.scalajs.js

import org.scalajs.dom.File

import com.greencatsoft.angularjs.core.HttpPromise
import com.greencatsoft.angularjs.injectable

/**
 * Wrapper for the angular-file-upload module.
 *
 * @see https://github.com/danialfarid/angular-file-upload
 */
@injectable("$upload")
trait FileUpload extends js.Object {

  def upload(config: FileUploadConfig): FileUploadPromise = ???
}

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

trait FileUploadPromise extends HttpPromise {

  def progress(listener: js.Function1[ProgressEvent, Unit]): this.type = ???

  def abort(): Unit = ???
}

class ProgressEvent extends js.Object {

  val loaded: Double = ???

  val total: Double = ???
}