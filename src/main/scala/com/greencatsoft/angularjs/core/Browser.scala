package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

@injectable("$document")
trait Document extends org.scalajs.dom.Document

@injectable("$window")
trait Window extends org.scalajs.dom.Window
