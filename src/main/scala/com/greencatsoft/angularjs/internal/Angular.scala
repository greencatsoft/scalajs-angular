package com.greencatsoft.angularjs.internal

import com.greencatsoft.angularjs.core.Injector

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context
import scala.scalajs.js
import scala.scalajs.js.UndefOr
import org.scalajs.dom.Element
import com.greencatsoft.{ angularjs => api }
import com.greencatsoft.angularjs.{ AngularElement, Config, Controller, Directive, Factory, Service, Runnable }
import com.greencatsoft.angularjs.Filter

@js.native
private[angularjs] trait Angular extends js.Object {

  def injector(): Injector = js.native

  def module(name: String): UndefOr[Module] = js.native

  def module(name: String, require: js.Array[String]): Module = js.native

  def element(elem: Element): AngularElement = js.native

  def extend(dst: js.Object, src: js.Object*): js.Object = js.native

  def merge(dst: js.Object, src: js.Object*): js.Object = js.native

}

private[angularjs] object Angular {

  import ServiceProxy.{ identifier, newClassWrapper, newObjectWrapper }

  def config[A <: Config](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newObjectWrapper(c)(target)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$config($proxy)}")
  }

  def configFromClass[A <: Config](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newClassWrapper(c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$config($proxy)}")
  }

  def controller[A <: Controller[_]](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newObjectWrapper(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$controller($name, $proxy)}")
  }

  def controllerFromClass[A <: Controller[_]](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newClassWrapper(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$controller($name, $proxy)}")
  }

  def directive[A <: Directive](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newObjectWrapper(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.module.directive($name, $proxy)}")
  }

  def directiveFromClass[A <: Directive](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newClassWrapper(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$directive($name, $proxy)}")
  }

  def factory[A <: Factory[_]](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newObjectWrapper(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$factory($name, $proxy)}")
  }

  def factoryFromClass[A <: Factory[_]](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newClassWrapper(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$factory($name, $proxy)}")
  }

  def run[A <: Runnable](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newObjectWrapper(c)(target)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$run($proxy)}")
  }

  def runFromClass[A <: Runnable](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newClassWrapper(c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$run($proxy)}")
  }

  def service[A <: Service](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newObjectWrapper(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$service($name, $proxy)}")
  }

  def filter[A <: Filter[_]](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newObjectWrapper(c)(target)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$filter($name, $proxy)}")
  }

  def filterFromClass[A <: Filter[_]](c: Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newClassWrapper(c)
    val name = moduleName[A](c)

    c.Expr[api.Module](q"{${c.prefix.tree}.$$filter($name, $proxy)}")
  }

  private def moduleName[A <: Service](c: Context)(implicit tag: c.WeakTypeTag[A]): c.universe.Literal = {
    import c.universe._

    val name = identifier[A](c) getOrElse {
      c.abort(c.enclosingPosition, s"The specified type '${tag.tpe}' does not have @injectable annotation.")
    }

    Literal(Constant(name))
  }
}
