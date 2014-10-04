package com.greencatsoft.angularjs.i18next

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav

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