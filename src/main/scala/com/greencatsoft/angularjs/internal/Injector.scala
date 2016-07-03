package com.greencatsoft.angularjs.internal

import com.greencatsoft.angularjs.core

import scala.reflect.macros.blackbox

private[angularjs] object Injector {

  def get[A](c: blackbox.Context)(injector: c.Expr[core.Injector])(implicit tag: c.WeakTypeTag[A]): c.Expr[A] = {
    import c.universe._

    val name = ServiceProxy.identifierFromType(c)(tag.tpe) getOrElse {
      c.abort(c.enclosingPosition, s"The specified type '${tag.tpe}' does not have @injectable annotation.")
    }
    val nameExpr = c.Expr[String](q"$name")
    reify { injector.splice.get[A](nameExpr.splice) }
  }
}
