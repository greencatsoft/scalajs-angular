package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav

/**
 * Wrapper for the ng-i18next module
 *
 * @see https://github.com/archer96/ng-i18next
 */
@js.native
@injectable("jm.i18next")
trait I18next extends js.Object

@js.native
@injectable("$i18nextProvider")
trait I18nextProvider extends js.Object {
  var options: I18nextOptions = js.native
}

@js.native
trait I18nextOptions extends js.Object {

  var lng: String = js.native

  var detectLngQS: String = js.native

  var cookieName: String = js.native

  var cookieDomain: String = js.native

  var useCookie: Boolean = js.native

  var ns: NamespaceConfig = js.native

  var fallbackLng: String = js.native

  var resGetPath: String = js.native
}

@js.native
trait NamespaceConfig extends js.Object {

  var namespaces: js.Array[String] = js.native

  var defaultNs: String = js.native
}

object NamespaceConfig {

  def apply(namespace: String): NamespaceConfig = {
    require(namespace != null, "Missing argument 'namespace'.")

    apply(Array(namespace))
  }

  def apply(namespaces: Array[String], defaultNs: Option[String] = None): NamespaceConfig = {
    require(namespaces != null, "Missing argument 'namespaces'.")

    val config = new js.Object().asInstanceOf[NamespaceConfig]

    config.namespaces = namespaces.toJSArray
    config.defaultNs = defaultNs.getOrElse(namespaces(0))

    config
  }
}
