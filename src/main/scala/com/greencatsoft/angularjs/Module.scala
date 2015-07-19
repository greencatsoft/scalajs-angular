package com.greencatsoft.angularjs

import scala.language.experimental.macros
import scala.scalajs.js

class Module private[angularjs] (val module: internal.Module) {
  require(module != null, "Missing argument 'module'.")

  import internal.{ Angular => angular }

  def config[A <: Config](target: A): Unit = macro angular.config[A]

  def config[A <: Config]: Unit = macro angular.configFromClass[A]

  def controller[A <: Controller[_]](target: A): Unit = macro angular.controller[A]

  def controller[A <: Controller[_]]: Unit = macro angular.controllerFromClass[A]

  def directive[A <: Directive](target: A): Unit = macro angular.directive[A]

  def directive[A <: Directive]: Unit = macro angular.directiveFromClass[A]

  def factory[A <: Factory[_]](target: A): Unit = macro angular.factory[A]

  def factory[A <: Factory[_]]: Unit = macro angular.factoryFromClass[A]

  def run[A <: Runnable](target: A): Unit = macro angular.run[A]

  def run[A <: Runnable]: Unit = macro angular.runFromClass[A]

  def service[A <: Service](target: A): Unit = macro angular.service[A]

  def filter[A <: Filter[_]](target: A): Unit = macro angular.filter[A]

  def filter[A <: Filter[_]]: Unit = macro angular.filterFromClass[A]
}
