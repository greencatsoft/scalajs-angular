package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable
import com.greencatsoft.angularjs.internal.ServiceProxy

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context
import scala.scalajs.js

@injectable("$injector")
@js.native
trait Injector extends js.Object {
  def get[A](name: String): A = js.native
}

/** Supports easy, type safe injection of types annotated with `@injectable`.
  *
  * See example:
  * {{{
  * implicit val injector = Angular.injector("ng", "myModule")
  * val compile = Injector.get[Compile]
  * val rootScope = Injector.get[RootScope]
  * }}}
  */
object Injector {
  /** Gets an instance of the specified type from the provided injector.
    *
    * Fails at compile time, if the type isn't annotated with `@injectable`.
    *
    * @param injector the injector to retrieve the type from
    * @tparam A the type to return an instance of
    * @return instance of the specified type provided by the injector
    */
  def get[A](implicit injector: Injector): A = macro get_impl[A]

  def get_impl[A](c: Context)(injector: c.Expr[Injector])(implicit tag: c.WeakTypeTag[A]): c.Expr[A] = {
    import c.universe._

    val name = ServiceProxy.identifierFromType(c)(tag.tpe) getOrElse {
      c.abort(c.enclosingPosition, s"The specified type '${tag.tpe}' does not have @injectable annotation.")
    }
    val nameExpr = c.Expr[String](q"$name")
    reify { injector.splice.get[A](nameExpr.splice) }
  }
}