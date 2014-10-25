package com.greencatsoft.angularjs.extensions

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav

import com.greencatsoft.angularjs.injectable

/**
 * Wrapper for the ng-i18next module
 *
 * @see https://github.com/archer96/ng-i18next
 */
@injectable("jm.i18next")
trait I18next extends js.Object

@injectable("$i18nextProvider")
trait I18nextProvider extends js.Object {
  var options: I18nextOptions = ???
}

trait I18nextOptions extends js.Object {

  var lng: String = ???

  var detectLngQS: String = ???

  var cookieName: String = ???

  var cookieDomain: String = ???

  var useCookie: Boolean = ???

  var ns: NamespaceConfig = ???

  var fallbackLng: String = ???

  var resGetPath: String = ???
}

trait NamespaceConfig extends js.Object {

  var namespaces: js.Array[String] = ???

  var defaultNs: String = ???
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