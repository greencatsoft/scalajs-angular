package com.greencatsoft.angularjs

import scala.language.experimental.macros

class Module private[angularjs] (val module: internal.Module) {
  require(module != null, "Missing argument 'module'.")

  import internal.{ Angular => angular }

  def config[A <: Config](target: A): Module = macro angular.config[A]

  def config[A <: Config]: Module = macro angular.configFromClass[A]

  def $config(constructor: ServiceDefinition[_ <: Config]): Module = {
    module.config(constructor)
    this
  }

  def controller[A <: Controller[_]](target: A): Module = macro angular.controller[A]

  def controller[A <: Controller[_]]: Module = macro angular.controllerFromClass[A]

  def $controller(name: String, constructor: ServiceDefinition[_ <: Controller[_]]): Module = {
    module.controller(name, constructor)
    this
  }

  def directive[A <: Directive](target: A): Module = macro angular.directive[A]

  def directive[A <: Directive]: Module = macro angular.directiveFromClass[A]

  def $directive(name: String, constructor: ServiceDefinition[_ <: Directive]): Module = {
    module.directive(name, constructor)
    this
  }

  def factory[A <: Factory[_]](target: A): Module = macro angular.factory[A]

  def factory[A <: Factory[_]]: Module = macro angular.factoryFromClass[A]

  def $factory(name: String, constructor: ServiceDefinition[_ <: Factory[_]]): Module = {
    module.factory(name, constructor)
    this
  }

  def run[A <: Runnable](target: A): Module = macro angular.run[A]

  def run[A <: Runnable]: Module = macro angular.runFromClass[A]

  def $run(constructor: ServiceDefinition[_ <: Runnable]): Module = {
    module.run(constructor)
    this
  }

  def service[A <: Service](target: A): Module = macro angular.service[A]

  def $service(name: String, constructor: ServiceDefinition[_]): Module = {
    module.service(name, constructor)
    this
  }

  def filter[A <: Filter[_]](target: A): Module = macro angular.filter[A]

  def filter[A <: Filter[_]]: Module = macro angular.filterFromClass[A]

  def $filter(name: String, constructor: ServiceDefinition[_ <: Filter[_]]): Module = {
    module.filter(name, constructor)
    this
  }
}
