package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

/**
 * Use the \$locationProvider to configure how the application deep linking paths are stored.
 *
 * @see https://docs.angularjs.org/api/ng/provider/\$locationProvider
 */
@injectable("$locationProvider")
trait LocationProvider extends js.Object {

  /**
   * @param prefix Prefix for hash part (containing path and search)
   * @return current value if used as getter or itself (chaining) if used as setter
   */
  def hashPrefix(prefix: String = null): String = js.native

  /**
   * @param mode If boolean, sets html5Mode.enabled to value. If object, sets enabled, requireBase and rewriteLinks to
   *             respective values. Supported properties:
   * @return html5Mode object if used as getter or itself (chaining) if used as setter
   */
  def html5Mode(mode: Boolean): Html5ModeInfo = js.native

  /**
   * @param mode If boolean, sets html5Mode.enabled to value. If object, sets enabled, requireBase and rewriteLinks to
   *             respective values. Supported properties:
   * @return html5Mode object if used as getter or itself (chaining) if used as setter
   */
  def html5Mode(mode: Html5ModeInfo): Html5ModeInfo = js.native
}

trait Html5ModeInfo extends js.Object {

  /**
   * (default: false) If true, will rely on history.pushState to change urls where supported. Will fall back to
   * hash-prefixed paths in browsers that do not support pushState.
   */
  var enabled: Boolean = js.native

  /**
   * (default: true) When html5Mode is enabled, specifies whether or not a tag is required to be present. If enabled
   * and requireBase are true, and a base tag is not present, an error will be thrown when \$location is injected. See
   * the \$location guide for more information
   */
  var requireBase: Boolean = js.native

  /**
   * (default: true) When html5Mode is enabled, enables/disables url rewriting for relative links.
   */
  var rewriteLinks: Boolean = js.native
}

object Html5ModeInfo {

  def apply(enabled: Boolean = false, requireBase: Boolean = true, rewriteLinks: Boolean = true): Html5ModeInfo = {
    val mode = new js.Object().asInstanceOf[Html5ModeInfo]

    mode.enabled = enabled
    mode.requireBase = requireBase
    mode.rewriteLinks = rewriteLinks

    mode
  }
}

@injectable("$location")
trait Location extends js.Object {

  def absUrl(): String = js.native

  def url(url: String = null, replace: String = null): String = js.native

  def protocol(): String = js.native

  def host(): String = js.native

  def port(): Int = js.native

  def path(): String = js.native

  def path(path: String): Location = js.native

  // TODO: refine argument types?
  def search(search: js.Any, paramValue: js.Any = null): js.Object = js.native

  def hash(hash: String = null): String = js.native

  def replace(): Unit = js.native
}
