package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.core.{ Promise, Scope }
import com.greencatsoft.angularjs.{ AngularElement, ServiceDefinition, injectable }

import scala.scalajs.js
import scala.scalajs.js.|

/**
 * Wrapper for the modal service provided by Angular directives for Bootstrap.
 * @see http://angular-ui.github.io/bootstrap/#/modal
 */
@js.native
@injectable("$uibModal")
trait ModalService extends js.Object {

  def open[T](options: ModalOptions): ModalInstance[T] = js.native
}

@js.native
trait ModalOptions extends js.Object {

  var animation: Boolean = js.native

  var appendTo: AngularElement = js.native

  var template: String = js.native

  var templateUrl: String = js.native

  var controller: String | ServiceDefinition[_] = js.native

  var scope: Any = js.native

  var size: String = js.native

  var backdropClass: String = js.native

  var windowClass: String = js.native

  var backdrop: Boolean | String = js.native

  var keyboard: Boolean = js.native

  var resolve: js.Dictionary[Any] = js.native
}

object ModalOptions {

  def apply(): ModalOptions = {
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

  def dismiss(reason: Any): Unit = js.native

  def result: Promise[T] = js.native

  def opened: Promise[Boolean] = js.native

  def closed: Promise[Unit] = js.native

  def rendered: Promise[Unit] = js.native
}

@js.native
@injectable("$uibModalStack")
trait ModalStack extends js.Object {

  def close[T](key: String, result: T): Unit = js.native

  def dismiss(key: String): Unit = js.native

  def dismiss(key: String, reason: Any): Unit = js.native

  def dismissAll(): Unit = js.native

  def dismissAll(reason: Any): Unit = js.native
}

@js.native
trait ModalScope[T] extends Scope {

  def $close(result: T): Unit = js.native

  def $close(): Unit = js.native

  def $dismiss(reason: Any): Unit = js.native

  def $dismiss(): Unit = js.native
}
