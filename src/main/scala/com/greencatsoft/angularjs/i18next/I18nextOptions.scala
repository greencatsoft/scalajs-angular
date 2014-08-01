package com.greencatsoft.angularjs.i18next

import scala.scalajs.js

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