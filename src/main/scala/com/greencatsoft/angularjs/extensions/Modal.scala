package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.core.Promise
import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

/**
 * Wrapper for the modal service provided by Angular directives for Bootstrap.
 *
 * @see http://angular-ui.github.io/bootstrap/#/modal
 */
@js.native
@injectable("$uibModal")
trait ModalService extends js.Object {

  def open[T](options: ModalOptions): ModalInstance[T] = js.native
}

@js.native
trait ModalOptions extends js.Object {

  var template: String = js.native

  var templateUrl: String = js.native

  var controller: String = js.native

  var scope: Any = js.native

  var size: String = js.native

  var windowClass: String = js.native

  var backdrop: Boolean = js.native

  var keyboard: Boolean = js.native

  var resolve: js.Dictionary[js.Any] = js.native
}

object ModalOptions {

  def apply() = {
    val options = new js.Object().asInstanceOf[ModalOptions]

    options.size = "lg"
    options.resolve = js.Dictionary.empty

    options
  }
}

@js.native
@injectable("$uibModalInstance")
trait ModalInstance[T] extends js.Object {

  def close(result: T): Unit = js.native

  def close(): Unit = js.native

  def dismiss(reason: js.Any): Unit = js.native

  def result: Promise[T] = js.native

  def opened: Promise[T] = js.native
}
