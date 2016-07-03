package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.{injectable, internal}

import scala.language.experimental.macros
import scala.scalajs.js

@injectable("$injector")
@js.native
trait Injector extends js.Object {
  def get[A](name: String): A = js.native
}

/**
  * Supports easy, type safe injection of types annotated with `@injectable`.
  *
  * See example:
  * {{{
  * implicit val injector = Angular.injector("ng", "myModule")
  * val compile = Injector.get[Compile]
  * val rootScope = Injector.get[RootScope]
  * }}}
  */
object Injector {

  /**
    * Gets an instance of the specified type from the provided injector.
    *
    * Fails at compile time, if the type isn't annotated with `@injectable`.
    *
    * @param injector the injector to retrieve the type from
    * @tparam A the type to return an instance of
    * @return instance of the specified type provided by the injector
    */
  def get[A](implicit injector: Injector): A = macro internal.Injector.get[A]
}