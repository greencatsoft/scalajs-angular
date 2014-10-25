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

  def open(options: ModalOptions): ModalInstance = ???
}

trait ModalOptions extends js.Object {

  var templateUrl: String = _

  var controller: String = _

  var scope: Any = _

  var size: String = _

  var windowClass: String = _

  var backdrop = true

  var keyboard = true
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

  def close(result: js.Any): Unit = ???

  def close(): Unit = ???

  def dismiss(reason: js.Any): Unit = ???

  def result: Promise = ???

  def opened: Promise = ???
}
