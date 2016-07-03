package com.greencatsoft.angularjs.extensions.material

import com.greencatsoft.angularjs.core.Promise
import com.greencatsoft.angularjs.injectable
import org.scalajs.dom

import scala.scalajs.js

/**
  * @see https://material.angularjs.org/#/api/material.components.bottomSheet/service/\$mdBottomSheet
  */
@js.native
@injectable("$mdBottomSheet")
trait BottomSheet extends BottomSheetHideOrCancel {

  /**
    * Show a bottom sheet with the specified options.
    *
    * @param options An options object, with the following properties:
    * @return A promise that can be resolved with \$mdBottomSheet.hide() or rejected with \$mdBottomSheet.cancel().
    */
  def show(options: BottomSheetOptions): Promise[BottomSheetHideOrCancel] = js.native
}

@js.native
trait BottomSheetHideOrCancel extends js.Object {

  /**
    * Hide the existing bottom sheet and resolve the promise returned from \$mdBottomSheet.show().
    *
    * @param response An argument for the resolved promise.
    */
  def hide(response: Any): Unit = js.native

  /**
    * Hide the existing bottom sheet and reject the promise returned from \$mdBottomSheet.show().
    *
    * @param response An argument for the rejected promise.
    */
  def cancel(response: Any): Unit = js.native
}

@js.native
trait BottomSheetOptions extends js.Object {

  /**
    * The url of an html template file that will be used as the content of the bottom sheet. Restrictions: the template
    * must have an outer md-bottom-sheet element.
    */
  var templateUrl: String = js.native

  /** Same as templateUrl, except this is an actual template string. */
  var template: String = js.native

  /** The controller to associate with this bottom sheet. */
  var controller: js.Any = js.native

  /**
    * An object containing key/value pairs. The keys will be used as names of values to inject into the controller. For
    * example, locals: {three: 3} would inject three into the controller with the value of 3.
    */
  var locals: String = js.native

  /**
    * A click's event object. When passed in as an option, the location of the click will be used as the starting point
    * for the opening animation of the the dialog.
    */
  var targetEvent: dom.MouseEvent = js.native

  /**
    * Similar to locals, except it takes promises as values and the bottom sheet will not open until the promises
    * resolve.
    */
  //TODO var resolve:js.Object = js.native

  /** An alias to assign the controller to on the scope. */
  var controllerAs: String = js.native

  /** The element to append the bottom sheet to. Defaults to appending to the root element of the application. */
  var parent: dom.Element = js.native

  /** Whether to disable scrolling while the bottom sheet is open. Default true. */
  var disableParentScroll: Boolean = js.native

  var bindToController: Boolean = js.native
}
