package com.greencatsoft.angularjs.fileupload

import scala.scalajs.js

import com.greencatsoft.angularjs.HttpConfig

/**
 * Wrapper for the angular-file-upload module.
 *
 * @see https://github.com/danialfarid/angular-file-upload
 */
trait FileUpload extends js.Object {

  def upload(config: FileUploadConfig): FileUploadPromise = ???
}

object FileUpload {

  val Name = "$upload"
}
