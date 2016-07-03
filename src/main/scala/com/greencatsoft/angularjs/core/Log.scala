package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

/** Use the \$logProvider to configure how the application logs messages
  *
  * @see https://docs.angularjs.org/api/ng/provider/\$logProvider
  */
@js.native
@injectable("$logProvider")
trait LogProvider extends js.Object {

  /** enable or disable debug level messages
    *
    * @param flag enable or disable debug level messages
    * @return current value if used as getter or itself (chaining) if used as setter
    */
  def debugEnabled(flag: Boolean): this.type = js.native

  /** enable or disable debug level messages
    *
    * @return current value if used as getter or itself (chaining) if used as setter
    */
  def debugEnabled(): Boolean = js.native
}

/** Simple service for logging. Default implementation safely writes the message into the browser's console (if present).
  * The main purpose of this service is to simplify debugging and troubleshooting.
  * The default is to log debug messages. You can use ng.\$logProvider#debugEnabled to change this.
  *
  * @see https://docs.angularjs.org/api/ng/service/\$log
  */
@js.native
@injectable("$log")
trait Log extends js.Object {

  /** Write a log message
    */
  def log(message: js.Any*): Unit = js.native

  /** Write an information message
    *
    * @param message The message to log.
    */
  def info(message: js.Any*): Unit = js.native

  /** Write a warning message
    *
    * @param message The message to log.
    */
  def warn(message: js.Any*): Unit = js.native

  /** Write an error message
    *
    * @param message The message to log.
    */
  def error(message: js.Any*): Unit = js.native

  /** Write a debug message
    *
    * @param message The message to log.
    */
  def debug(message: js.Any*): Unit = js.native
}
