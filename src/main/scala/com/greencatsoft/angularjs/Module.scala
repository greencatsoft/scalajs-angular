package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.directive.Directive

import scala.scalajs.js
import scala.scalajs.js.Any.{ fromFunction10, fromString }

trait Module extends js.Object {

  def controller(name: String, constructor: js.Array[js.Any]): Module = ???

  def config(constructor: js.Array[js.Any]): Module = ???

  def run(constructor: js.Array[js.Any]): Module = ???

  def directive(name: String, directiveFactory: js.Array[js.Any]): Module = ???
}

object Module {

  def apply(module: Module) = new ModuleProxy(module)
}

class ModuleProxy(val module: Module) {
  require(module != null, "Missing argument 'module'.")

  def config(target: InjectionTarget*): this.type = {
    require(target != null, "Missing argument 'target'.")

    target.foreach(t => register(t) { args: js.Array[js.Any] => module.config(args) })
    this
  }

  def controller(target: NamedTarget*): this.type = {
    require(target != null, "Missing argument 'target'.")

    target.foreach(t => register(t) { args: js.Array[js.Any] => module.controller(t.name, args) })
    this
  }

  def run(target: InjectionTarget*): this.type = {
    require(target != null, "Missing argument 'target'.")

    target.foreach(t => register(t) { args: js.Array[js.Any] => module.run(args) })
    this
  }

  def register(target: InjectionTarget)(fn: js.Array[js.Any] => Unit) {
    require(target != null, "Missing argument 'target'.")

    val handler = (a0: js.Any, a1: js.Any, a2: js.Any, a3: js.Any, a4: js.Any,
      a5: js.Any, a6: js.Any, a7: js.Any, a8: js.Any, a9: js.Any) => {

      target.inject(Seq(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9))
      target.initialize()
    }

    val args = js.Array[js.Any]()

    target.dependencies.foreach(d => args.push(d))
    args.push(handler)

    fn(args)
  }

  def directive(directives: Directive*): this.type = {
    require(directives != null, "Missing argument 'directives'.")

    def registerDirective(d: Directive) {
      val handler = (a0: js.Any, a1: js.Any, a2: js.Any, a3: js.Any, a4: js.Any,
                     a5: js.Any, a6: js.Any, a7: js.Any, a8: js.Any, a9: js.Any) => {
        d.inject(Seq(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9))
        d.initialize()
        d.ddo
      }

      val args = js.Array[js.Any]()

      d.dependencies.foreach(d => args.push(d))
      args.push(handler)

      module.directive(d.name,args)
    }

    directives.foreach( registerDirective )
    this
  }
}