package com.greencatsoft.angularjs.extensions.material

import scala.scalajs.js
import scala.scalajs.js.{ UndefOr, undefined, | }

import org.scalajs.dom.{ ClientRect, Element }

import com.greencatsoft.angularjs.{ injectable, AngularElement }
import com.greencatsoft.angularjs.core.{ Event, Promise, Scope }

@js.native
@injectable("$mdDialog")
trait MdDialog extends js.Object {
  /** Builds a preconfigured dialog with the specified message. */
  def alert(): MdDialogPreset = js.native
  /** Builds a preconfigured dialog with the specified message.
    * You can call show and the promise returned will be resolved
    * only if the user clicks the confirm action on the dialog. */
  def confirm(): MdDialogPreset = js.native
  /** Show a dialog with the specified options. */
  def show[T](options: MdDialogOptions): Promise[T] = js.native
  def show[T](preset: MdDialogPreset): Promise[T] = js.native
  /** Hide an existing dialog and resolve the promise returned from
    * `$mdDialog.show().`  Returns a promise that is resolved when
    * the dialog has been closed. */
  def hide[T](response: T): Promise[T] = js.native
  /** Hide an existing dialog and reject the
    * promise returned from $mdDialog.show(). */
  def cancel[T](response: T): Promise[T] = js.native
}

@js.native
trait MdDialogOptions extends js.Object {
  /** The url of a template that will be used as the content of the dialog. */
  val templateUrl: UndefOr[String]
  /** HTML template to show in the dialog. This must be trusted HTML with
    * respect to Angular's $sce service. This template should never be
    * constructed with any kind of user input or user data. */
  val template: UndefOr[String]
  /** Whether or not to automatically wrap the template with a
    * <md-dialog> tag if one is not provided. Defaults to true.
    * Can be disabled if you provide a custom dialog directive. */
  val autoWrap: UndefOr[Boolean]
  /** A click's event object. When passed in as an option, the
    * location of the click will be used as the starting point for
    * the opening animation of the the dialog. */
  val targetEvent: UndefOr[DOMClickEvent]
  /** The query selector, DOM element or the Rect object that is
    * used to determine the bounds (top, left, height, width) from
    * which the Dialog will originate. */
  val openFrom: UndefOr[String | Element | ClientRect]
  /** The query selector, DOM element or the Rect object that is
    * used to determine the bounds (top, left, height, width) to
    * which the Dialog will target. */
  val closeTo: UndefOr[String | Element | ClientRect]
  /** the scope to link the template / controller to. If none is
    * specified, it will create a new isolate scope. This scope will
    * be destroyed when the dialog is removed unless `preserveScope` is
    * set to true. */
  val scope: UndefOr[js.Object]
  /** whether to preserve the scope when the element is removed.
    * Default is false */
  val preserveScope: UndefOr[Boolean]
  /** Whether to disable scrolling while the dialog is open. Default true. */
  val disableParentScroll: UndefOr[Boolean]
  /** Whether there should be an opaque backdrop behind the dialog. Default true. */
  val hasBackdrop: UndefOr[Boolean]
  /** Whether the user can click outside the dialog to close it. Default false. */
  val clickOutsideToClose: UndefOr[Boolean]
  /** Whether the user can press escape to close the dialog. Default true. */
  val escapeToClose: UndefOr[Boolean]
  /** An option to override focus behavior on open. Only disable if
    * focusing some other way, as focus management is required for
    * dialogs to be accessible. Defaults to true. */
  val focusOnOpen: UndefOr[Boolean]
  /** The controller to associate with the dialog. The controller will
    * be injected with the local `$mdDialog`, which passes along a
    * scope for the dialog. */
  val controller: UndefOr[String]
  /** An object containing key/value pairs. The keys will be used as
    * names of values to inject into the controller. For example,
    * `locals: {three: 3}` would inject three into the controller, with
    * the value 3. If `bindToController` is true, they will be
    * copied to the controller instead. */
  val locals: UndefOr[js.Object]
  /** bind the locals to the controller, instead of passing them in.
    * These values will not be available until after initialization. */
  val bindToController: UndefOr[Boolean]
  /** Similar to locals, except it takes promises as values, and the
    * dialog will not open until all of the promises resolve. */
  val resolve: UndefOr[js.Dictionary[Promise[_]]]
  /** An alias to assign the controller to on the scope. */
  val controllerAs: UndefOr[String]
  /** The element to append the dialog to. Defaults to
    * appending to the root element of the application. */
  val parent: UndefOr[AngularElement]
  /** Callback function used to announce the show() action is starting. */
  val onShowing: UndefOr[js.Function]
  /** Callback function used to announce when the show() action is finished. */
  val onComplete: UndefOr[js.Function]
  /** Callback function used to announce the close/hide() action is
    * starting. This allows developers to run custom animations in
    * parallel the close animations. */
  val onRemoving: UndefOr[js.Function]
  /** An option to apply `.md-dialog-fullscreen` class on open. */
  val fullscreen: UndefOr[Boolean]
}
object MdDialogOptions {
  def apply(
    templateUrl: UndefOr[String] = undefined,
    template: UndefOr[String] = undefined,
    autoWrap: UndefOr[Boolean] = undefined,
    targetEvent: UndefOr[DOMClickEvent] = undefined,
//    openFrom: UndefOr[String | Element | ClientRect] = undefined,
//    closeTo: UndefOr[String | Element | ClientRect] = undefined,
//    openFrom: UndefOr[String | ClientRect] = undefined,
//    closeTo: UndefOr[String | ClientRect] = undefined,
//    openFrom: Element | ClientRect,
//    closeTo: Element | ClientRect,
    openFrom: UndefOr[String] = undefined,
    closeTo: UndefOr[String] = undefined,
    scope: UndefOr[js.Object] = undefined,
    preserveScope: UndefOr[Boolean] = undefined,
    disableParentScroll: UndefOr[Boolean] = undefined,
    hasBackdrop: UndefOr[Boolean] = undefined,
    clickOutsideToClose: UndefOr[Boolean] = undefined,
    escapeToClose: UndefOr[Boolean] = undefined,
    focusOnOpen: UndefOr[Boolean] = undefined,
    controller: UndefOr[String] = undefined,
    locals: UndefOr[js.Object] = undefined,
    bindToController: UndefOr[Boolean] = undefined,
    resolve: UndefOr[js.Dictionary[Promise[_]]] = undefined,
    controllerAs: UndefOr[String] = undefined,
    parent: UndefOr[AngularElement] = undefined,
    onShowing: UndefOr[js.Function] = undefined,
    onComplete: UndefOr[js.Function] = undefined,
    onRemoving: UndefOr[js.Function] = undefined,
    fullscreen: UndefOr[Boolean] = undefined
  ) =
    js.Dynamic.literal(
      "templateUrl" -> templateUrl,
      "template" -> template,
      "autoWrap" -> autoWrap,
      "targetEvent" -> targetEvent,
      "openFrom" -> openFrom,
      "closeTo" -> closeTo,
      "scope" -> scope,
      "preserveScope" -> preserveScope,
      "disableParentScroll" -> disableParentScroll,
      "hasBackdrop" -> hasBackdrop,
      "clickOutsideToClose" -> clickOutsideToClose,
      "escapeToClose" -> escapeToClose,
      "focusOnOpen" -> focusOnOpen,
      "controller" -> controller,
      "locals" -> locals,
      "bindToController" -> bindToController,
      "resolve" -> resolve,
      "controllerAs" -> controllerAs,
      "parent" -> parent,
      "onShowing" -> onShowing,
      "onComplete" -> onComplete,
      "onRemoving" -> onRemoving,
      "fullscreen" -> fullscreen
    ).asInstanceOf[MdDialogOptions]
}

/** Returned from `alert()`, and `confirm()` with chainable configuration methods. */
@js.native
trait MdDialogPreset extends js.Object {
  /** Sets the confirm title. */
  def title(string: String): MdDialogPreset = js.native
  /** Sets the confirm message. */
  def textContent(string: String): MdDialogPreset = js.native
  /** Sets the confirm message as HTML. Requires ngSanitize module to be loaded.
    * HTML is not run through Angular's compiler. */
  def htmlContent(string: String): MdDialogPreset = js.native
  /** Sets the confirm "Okay" button text. */
  def ok(string: String): MdDialogPreset = js.native
  /** Sets the confirm "Cancel" button text. */
  def cancel(string: String): MdDialogPreset = js.native
  /** Sets the theme of the confirm dialog. */
  def theme(string: String): MdDialogPreset = js.native
  /** A click's event object. When passed in as an option, the
    * location of the click will be used as the starting point for the
    * opening animation of the the dialog. */
  def targetEvent(event: DOMClickEvent): MdDialogPreset = js.native
}

@js.native
trait DOMClickEvent extends Event {
  val target: UndefOr[ClientRect] = js.native
}
