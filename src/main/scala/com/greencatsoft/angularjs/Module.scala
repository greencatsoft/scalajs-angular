package com.greencatsoft.angularjs

import scala.language.experimental.macros

class Module private[angularjs] (val module: internal.Module) {
  require(module != null, "Missing argument 'module'.")

  import internal.{ Angular => angular }

  def config[A <: Config](target: A): Module = macro angular.config[A]

  def controller[A <: Controller](target: A): Module = macro angular.controller[A]

  def directive[A <: Directive](target: A): Module = macro angular.directive[A]

  def factory[A <: Factory[_]](target: A): Module = macro angular.factory[A]

  def run[A <: Runnable](target: A): Module = macro angular.run[A]

  def service[A <: NamedService](target: A): Module = macro angular.service[A]
}
