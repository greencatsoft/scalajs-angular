package com.greencatsoft.angularjs.internal

import scala.reflect.macros.blackbox.Context
import scala.scalajs.js
import scala.scalajs.js.UndefOr

import org.scalajs.dom.Element

import com.greencatsoft.{ angularjs => api }
import com.greencatsoft.angularjs.{ AngularElement, Config, Controller, Directive, Factory, NamedService, Runnable }

import ServiceProxy.newInstance

private[angularjs] trait Angular extends js.Object {

  def module(name: String): UndefOr[Module] = ???

  def module(name: String, require: js.Array[String]): Module = ???

  def element(elem: Element): AngularElement = ???
}

private[angularjs] object Angular {

  import ServiceProxy.newInstance

  def config[A <: Config](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newInstance(c)(target)

    c.Expr[api.Module](q"{${c.prefix.tree}.module.config($proxy); ${c.prefix.tree}}")
  }

  def controller[A <: Controller](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newInstance(c)(target)
    val name = Select(target.tree, TermName("name"))

    c.Expr[api.Module](q"{${c.prefix.tree}.module.controller($name, $proxy); ${c.prefix.tree}}")
  }

  def directive[A <: Directive](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newInstance(c)(target)
    val name = Select(target.tree, TermName("name"))

    c.Expr[api.Module](q"{${c.prefix.tree}.module.directive($name, $proxy); ${c.prefix.tree}}")
  }

  def factory[A <: Factory[_]](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newInstance(c)(target)
    val name = Select(target.tree, TermName("name"))

    c.Expr[api.Module](q"{${c.prefix.tree}.module.factory($name, $proxy); ${c.prefix.tree}}")
  }

  def run[A <: Runnable](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newInstance(c)(target)

    c.Expr[api.Module](q"{${c.prefix.tree}.module.run($proxy); ${c.prefix.tree}}")
  }

  def service[A <: NamedService](c: Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[api.Module] = {
    import c.universe._

    val proxy = newInstance(c)(target)
    val name = Select(target.tree, TermName("name"))

    c.Expr[api.Module](q"{${c.prefix.tree}.module.service($name, $proxy); ${c.prefix.tree}}")
  }
}