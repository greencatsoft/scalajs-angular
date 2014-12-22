package com.greencatsoft.angularjs.extensions

import scala.scalajs.js

import com.greencatsoft.angularjs.injectable
import com.greencatsoft.angularjs.core.Promise

/**
 * Wrapper for the modal service provided by Angular directives for Bootstrap.
 *
 * @see http://angular-ui.github.io/bootstrap/#/modal
 */
@injectable("$modal")
trait ModalService extends js.Object {

  def open(options: ModalOptions): ModalInstance = js.native
}

trait ModalOptions extends js.Object {

  var templateUrl: String = js.native

  var controller: String = js.native

  var scope: Any = js.native

  var size: String = js.native

  var windowClass: String = js.native

  var backdrop : Boolean = js.native

  var keyboard : Boolean = js.native
}

object ModalOptions {

  def apply() = {
    val options = new js.Object().asInstanceOf[ModalOptions]

    options.size = "lg"

    options
  }
}

@injectable("$modalInstance")
trait ModalInstance extends js.Object {

  def close(result: js.Any): Unit = js.native

  def close(): Unit = js.native

  def dismiss(reason: js.Any): Unit = js.native

  def result: Promise = js.native

  def opened: Promise = js.native
}
