package com.greencatsoft.angularjs.internal

import com.greencatsoft.angularjs._
import com.greencatsoft.angularjs.core._
import com.greencatsoft.{ angularjs => api }

import org.scalajs.dom.Element

import scala.language.experimental.macros
import scala.reflect.macros.{blackbox => b}
import scala.scalajs.js
import scala.scalajs.js.{ UndefOr, | }

@js.native
private[angularjs] trait Angular extends js.Object {

  def bootstrap(element: Element, modules: js.Array[String]): Injector = js.native

  def injector(): Injector = js.native

  def injector(modules: js.Array[String]): Injector = js.native

  def module(name: String): UndefOr[Module] = js.native

  def module(name: String, require: js.Array[String]): Module = js.native

  def element(elem: Element): AngularElement = js.native

  def extend(dst: js.Object, src: js.Object*): js.Object = js.native

  def merge(dst: js.Object, src: js.Object*): js.Object = js.native

  def toJson(obj: Any): String = js.native

  def toJson(obj: Any, pretty: Boolean | Integer): String = js.native

  def fromJson(json: String): Any = js.native
}

private[angularjs] object Angular {

  import ServiceProxy.{definitionFromClass, definitionFromObject, identifier}

  def config[A <: Config](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromObject(c)(target)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$config($proxy)}")
  }

  def configFromClass[A <: Config](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromClass(c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$config($proxy)}")
  }

  def controller[A <: Controller[_]](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromObject(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$controller($name, $proxy)}")
  }

  def controllerFromClass[A <: Controller[_]](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromClass(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$controller($name, $proxy)}")
  }

  def directive[A <: Directive](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromObject(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.module.directive($name, $proxy)}")
  }

  def directiveFromClass[A <: Directive](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromClass(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$directive($name, $proxy)}")
  }

  def factory[A <: Factory[_]](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromObject(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$factory($name, $proxy)}")
  }

  def factoryFromClass[A <: Factory[_]](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromClass(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$factory($name, $proxy)}")
  }

  def run[A <: Runnable](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromObject(c)(target)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$run($proxy)}")
  }

  def runFromClass[A <: Runnable](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromClass(c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$run($proxy)}")
  }

  def service[A <: Service](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromObject(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$service($name, $proxy)}")
  }

  def filter[A <: Filter[_]](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromObject(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$filter($name, $proxy)}")
  }

  def filterFromClass[A <: Filter[_]](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = definitionFromClass(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$filter($name, $proxy)}")
  }

  private def moduleName[A <: Service](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.universe.Literal = {
    import c.universe._

    val name = identifier[A](c) getOrElse {
      c.abort(c.enclosingPosition, s"The specified type '${tag.tpe}' does not have @injectable annotation.")
    }

    Literal(Constant(name))
  }

  def nameOf[A](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[String] = {
    import c.universe._

    val name = ServiceProxy.identifierFromType(c)(tag.tpe) getOrElse {
      c.abort(c.enclosingPosition, s"The specified type '${tag.tpe}' does not have @injectable annotation.")
    }
    c.Expr[String](q"$name")
  }
}
