package com.greencatsoft.angularjs.fileupload

import scala.scalajs.js

import com.greencatsoft.angularjs.http.HttpPromise

trait FileUploadPromise extends HttpPromise {

  def progress(listener: js.Function1[ProgressEvent, Unit]): this.type = ???

  def abort(): Unit = ???
}

class ProgressEvent extends js.Object {

  val loaded: Double = ???

  val total: Double = ???
}