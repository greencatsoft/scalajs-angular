package com.greencatsoft.angularjs

import scala.language.experimental.macros
import scala.scalajs.js

class Module private[angularjs] (val module: internal.Module) {
  require(module != null, "Missing argument 'module'.")

  import internal.{ Angular => angular }

  def config[A <: Config](target: A): Module = macro angular.config[A]

  def config[A <: Config]: Module = macro angular.configFromClass[A]

  def controller[A <: Controller[_]](target: A): Module = macro angular.controller[A]

  def controller[A <: Controller[_]]: Module = macro angular.controllerFromClass[A]

  def directive[A <: Directive](target: A): Module = macro angular.directive[A]

  def directive[A <: Directive]: Module = macro angular.directiveFromClass[A]

  def factory[A <: Factory[_]](target: A): Module = macro angular.factory[A]

  def factory[A <: Factory[_]]: Module = macro angular.factoryFromClass[A]

  def run[A <: Runnable](target: A): Module = macro angular.run[A]

  def run[A <: Runnable]: Module = macro angular.runFromClass[A]

  def service[A <: Service](target: A): Module = macro angular.service[A]

  def filter[A <: Filter[_]](target: A): Module = macro angular.filter[A]

  def filter[A <: Filter[_]]: Module = macro angular.filterFromClass[A]
}
