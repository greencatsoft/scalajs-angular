package com.greencatsoft.angularjs.test

import com.greencatsoft.angularjs.core.Timeout
import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

object AngularMocks {
  val ModuleName = "ngMock"

  @js.native
  @injectable("$timeout")
  trait TimeoutMock extends Timeout {

    def flush(): Unit = js.native
  }
}