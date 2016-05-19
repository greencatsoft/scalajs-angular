package com.greencatsoft.angularjs

import scala.language.experimental.macros
import scala.language.implicitConversions

import com.greencatsoft.angularjs.internal.ServiceProxy

import scala.scalajs.js
import scala.scalajs.js.UndefOr

trait Service extends Initializable

@js.native
trait ServiceWrapper[A <: Service] extends js.Object {

  def service: A = js.native
}

object ServiceWrapper {

  def apply[A <: Service](wrapper: js.Object): Option[ServiceWrapper[A]] = ServiceProxy.unbind[A](wrapper)

  implicit def wrapperToService[A <: Service](wrapper: ServiceWrapper[A]): A = wrapper.service
}

@js.native
trait ServiceDefinition[A <: Service] extends js.Array[Any]

object ServiceDefinition {

  def apply[A <: Service]: ServiceDefinition[A] = macro ServiceProxy.definitionFromClass[A]

  def apply[A <: Service](target: A): ServiceDefinition[A] = macro ServiceProxy.definitionFromObject[A]
}