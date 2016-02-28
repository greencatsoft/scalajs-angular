package com.greencatsoft.angularjs.extensions.material

import scalajs.js
import scalajs.js.{ UndefOr, undefined }

import com.greencatsoft.angularjs.injectable

@js.native
@injectable("$mdToast")
trait MdToast extends js.Object {
  def show(present: MdToastPresent): com.greencatsoft.angularjs.core.Promise[Unit] = js.native
  def show(options: MdToastOptions): com.greencatsoft.angularjs.core.Promise[Unit] = js.native
  def simple(): MdToastPresent = js.native
}

@js.native
trait MdToastOptions extends js.Object {
  val controller: UndefOr[String] = js.native
  val templateUrl: UndefOr[String] = js.native
  val template: UndefOr[String] = js.native
  val hideDelay: UndefOr[Int] = js.native
  val position: UndefOr[String] = js.native
}
object MdToastOptions {
  def apply(controller: UndefOr[String] = undefined,
            templateUrl: UndefOr[String] = undefined,
            template: UndefOr[String] = undefined,
            hideDelay: UndefOr[Int] = undefined,
            position: UndefOr[String] = undefined): MdToastOptions =
    js.Dynamic.literal(
      "controller" -> controller,
      "templateUrl" -> templateUrl,
      "template" -> template,
      "hideDelay" -> hideDelay,
      "position" -> position
    ).asInstanceOf[MdToastOptions]
}

@js.native
trait MdToastPresent extends js.Object {

  def content(c: String): MdToastPresent = js.native

  def action(a: String): MdToastPresent = js.native

  def highlightAction(h: Boolean): MdToastPresent = js.native

  def capsule(h: Boolean): MdToastPresent = js.native

  def theme(h: Boolean): MdToastPresent = js.native

  def position(p: String): MdToastPresent = js.native

  def hideDelay(delay: Int): MdToastPresent = js.native

}
