package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.language.implicitConversions
import scala.scalajs.js

/**
 * \$sce is a service that provides Strict Contextual Escaping services to AngularJS.
 *
 * @see https://docs.angularjs.org/api/ng/service/\$sce
 */
@js.native
@injectable("$sce")
trait SceService extends js.Object {

  def isEnabled: Boolean = js.native

  def parseAs(`type`: String, expression: String): js.Function2[js.Any, js.Any, js.Any] = js.native

  def trustAs(`type`: String, value: js.Any): js.Any = js.native

  def trustAsHtml(value: js.Any): js.Any = js.native

  def trustAsUrl(value: js.Any): js.Any = js.native

  def trustAsResourceUrl(value: js.Any): js.Any = js.native

  def trustAsJs(value: js.Any): js.Any = js.native

  def getTrusted(`type`: String, maybeTrusted: js.Any): js.Any = js.native

  def getTrustedHtml(value: js.Any): js.Any = js.native

  def getTrustedCss(value: js.Any): js.Any = js.native

  def getTrustedUrl(value: js.Any): js.Any = js.native

  def getTrustedResourceUrl(value: js.Any): js.Any = js.native

  def getTrustedJs(value: js.Any): js.Any = js.native

  def parseAsHtml(expression: String): js.Function2[js.Any, js.Any, js.Any] = js.native

  def parseAsCss(expression: String): js.Function2[js.Any, js.Any, js.Any] = js.native

  def parseAsUrl(expression: String): js.Function2[js.Any, js.Any, js.Any] = js.native

  def parseAsResourceUrl(expression: String): js.Function2[js.Any, js.Any, js.Any] = js.native

  def parseAsJs(expression: String): js.Function2[js.Any, js.Any, js.Any] = js.native
}

/**
 * The \$sceProvider provider allows developers to configure the \$sce service.
 *
 * @see https://docs.angularjs.org/api/ng/provider/\$sceProvider
 */
@js.native
@injectable("$sceProvider")
trait SceProvider extends js.Object {

  def enabled: Boolean = js.native

  def enabled(value: Boolean): Boolean = js.native
}

/**
 * The \$sceDelegateProvider provider allows developers to configure the \$sceDelegate service. This allows one to
 * get/set the whitelists and blacklists used to ensure that the URLs used for sourcing Angular templates are safe.
 * Refer \$sceDelegateProvider.resourceUrlWhitelist and \$sceDelegateProvider.resourceUrlBlacklist
 *
 * @see https://docs.angularjs.org/api/ng/provider/\$sceDelegateProvider
 */
@js.native
@injectable("$sceDelegateProvider")
trait SceDelegateProvider extends js.Object {

  def resourceUrlWhitelist(whitelist: js.Array[String]): js.Array[String] = js.native

  def resourceUrlBlacklist(blacklist: js.Array[String]): js.Array[String] = js.native
}
