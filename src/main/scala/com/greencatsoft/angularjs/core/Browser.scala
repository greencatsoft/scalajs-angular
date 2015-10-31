package com.greencatsoft.angularjs.core

import scala.scalajs.js

import com.greencatsoft.angularjs.injectable

@js.native
@injectable("$document")
trait Document extends org.scalajs.dom.Document

@js.native
@injectable("$window")
trait Window extends org.scalajs.dom.Window
