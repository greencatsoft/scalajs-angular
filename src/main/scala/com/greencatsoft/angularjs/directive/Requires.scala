package com.greencatsoft.angularjs.directive

import scala.language.implicitConversions
import scala.scalajs.js

import com.greencatsoft.angularjs.{ ConfigBuilder, NamedTarget }

trait Requires extends ConfigBuilder {
  this: Directive =>

  var requirements = Set.empty[Requirement]

  abstract override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("require") = js.Array[String](requirements.toSeq.map(_.toString): _*)

    super.buildConfig(config)
  }

  case class Requirement(name: String, lookup: Boolean, optional: Boolean = false) {

    override def toString = (if (lookup) "^" else "") + (if (optional) "?" else "") + name
  }

  def ^(requirement: NamedTarget) = new Requirement(requirement.name, true)

  def ^?(requirement: NamedTarget) = new Requirement(requirement.name, true, true)

  def ?(requirement: NamedTarget) = new Requirement(requirement.name, false, true)

  implicit def ~(requirement: NamedTarget) = new Requirement(requirement.name, false)
}
