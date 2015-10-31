package com.greencatsoft.angularjs.extensions.material

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

/** \$mdSidenav makes it easy to interact with multiple sidenavs in an app.
  *
  * @see https://material.angularjs.org/#/api/material.components.sidenav/service/\$mdSidenav
  */
@js.native
@injectable("$mdSidenav")
trait Sidenav extends js.Object {

  def apply(componentId: String): this.type = js.native

  /** Toggle the given sidenav */
  def toggle(): Unit = js.native

  /** Open the given sidenav */
  def open(): Unit = js.native

  /** Close the given sidenav */
  def close(): Unit = js.native

  /** Exposes whether given sidenav is set to be open */
  def isOpen: Boolean = js.native

  /** Exposes whether given sidenav is locked open
    * If this is true, the sidenav will be open regardless of isOpen()
    */
  def isLockedOpen: Boolean = js.native
}