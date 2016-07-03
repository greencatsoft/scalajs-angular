package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.core.HttpPromise
import com.greencatsoft.angularjs.injectable

import org.scalajs.dom.File

import scala.scalajs.js
import scala.scalajs.js.|

/**
 * Wrapper for the ng-file-upload module.
 *
 * @see https://github.com/danialfarid/ng-file-upload
 */
@js.native
@injectable("Upload")
trait FileUpload extends js.Object {

  def upload(config: FileUploadConfig): FileUploadPromise = js.native
}

@js.native
trait FileUploadConfig extends js.Object {

  var url: String = js.native

  var data: Any = js.native

  var file: File | js.Array[File] = js.native

  var method: String = js.native

  var fileName: js.Any = js.native

  var fileFormDataName: js.Any = js.native

  var arrayKey: String = js.native
}

object FileUploadConfig {

  def apply(url: String, file: File | js.Array[File], data: Option[Any] = None, arrayKey: Option[String] = None) = {
    require(url != null, "Missing argument 'url'.")
    require(file != null, "Missing argument 'file'.")

    val config = new js.Object().asInstanceOf[FileUploadConfig]

    config.url = url
    config.file = file

    arrayKey.foreach(config.arrayKey = _)

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
