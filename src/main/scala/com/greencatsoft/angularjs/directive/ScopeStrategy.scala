package com.greencatsoft.angularjs.directive

import scala.scalajs.js
import scala.scalajs.js.Any.fromBoolean

import com.greencatsoft.angularjs.ConfigBuilder

trait ScopeStrategy extends ConfigBuilder {
  this: Directive =>
}

trait InheritParentScope extends ScopeStrategy {
  this: Directive =>

  override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("scope") = true

    super.buildConfig(config)
  }
}

trait UseParentScope extends ScopeStrategy {
  this: Directive =>

  override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("scope") = false

    super.buildConfig(config)
  }
}

trait IsolatedScope extends ScopeStrategy {
  this: Directive =>

  var bindings = Seq.empty[ScopeBinding]

  override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    val dict = js.Dictionary.empty[String]

    bindings foreach { b =>
      dict(b.name) = s"${b.prefix}${b.attribute}"
    }

    config("scope") = dict

    super.buildConfig(config)
  }

  abstract class ScopeBinding(val prefix: String) {

    val name: String

    val attribute: String
  }

  case class UnidirectionalBinding(name: String, attribute: String = "") extends ScopeBinding("=")

  case class BidirectionalBinding(name: String, attribute: String = "") extends ScopeBinding("@")

  case class BehavioralBinding(name: String, attribute: String = "") extends ScopeBinding("&")

  implicit class BindingBuilder(name: String) {

    def :=(attribute: String = ""): ScopeBinding = UnidirectionalBinding(name, attribute)

    def :@(attribute: String = ""): ScopeBinding = BidirectionalBinding(name, attribute)

    def :&(attribute: String = ""): ScopeBinding = BehavioralBinding(name, attribute)
  }
}