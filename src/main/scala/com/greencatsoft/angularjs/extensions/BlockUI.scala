package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.core.HttpConfig
import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

/**
 * Wrapper for the angular-block-ui module.
 *
 * @see https://github.com/McNull/angular-block-ui
 */
@js.native
@injectable("blockUI")
trait BlockUI extends js.Object {

  def start(): Unit = js.native

  def start(message: String): Unit = js.native

  def stop(): Unit = js.native

  def message(): Unit = js.native

  def done(callback: js.Function0[Unit]): Unit = js.native

  def isBlocking(): Boolean = js.native
}

@js.native
@injectable("blockUIConfig")
trait BlockUIConfig extends js.Object {

  var message: String = js.native

  var delay: Integer = js.native

  var template: String = js.native

  var templateUrl: String = js.native

  var autoBlock: Boolean = js.native

  var resetOnException: Boolean = js.native

  var requestFilter: js.Function1[HttpConfig, Unit] = js.native

  var autoInjectBodyBlock: Boolean = js.native

  var cssClass: String = js.native

  var blockBrowserNavigation: Boolean = js.native
}
