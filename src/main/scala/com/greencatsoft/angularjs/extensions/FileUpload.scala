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
@js.native
@injectable("$upload")
trait FileUpload extends js.Object {

  def upload(config: FileUploadConfig): FileUploadPromise = js.native
}

@js.native
trait FileUploadConfig extends js.Object {

  var url: String = js.native

  var data: Any = js.native

  var file: File = js.native

  var method: String = js.native

  var fileName: js.Any = js.native

  var fileFormDataName: js.Any = js.native
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

@js.native
trait FileUploadPromise extends HttpPromise[js.Any] {

  def progress(listener: js.Function1[ProgressEvent, Unit]): this.type = js.native

  def abort(): Unit = js.native
}

@js.native
class ProgressEvent extends js.Object {

  val loaded: Double = js.native

  val total: Double = js.native
}
