package com.greencatsoft.angularjs

import scala.scalajs.js
import scala.scalajs.js.Any.{ fromFunction10, fromString }
import scala.scalajs.js.ThisFunction10
import scala.scalajs.js.UndefOr
import com.greencatsoft.angularjs.controller.Controller
import com.greencatsoft.angularjs.directive.Directive

trait Module extends js.Object {

  def factory(name: String, constructor: js.Array[js.Any]): Module = ???

  def controller(name: String, constructor: js.Array[js.Any]): Module = ???

  def config(constructor: js.Array[js.Any]): Module = ???

  def run(constructor: js.Array[js.Any]): Module = ???

  def directive(name: String, directiveFactory: js.Array[js.Any]): Module = ???
}

object Module {

  def apply(module: Module) = new ModuleProxy(module)

  def asService(target: InjectionTarget)(fn: js.Array[js.Any] => Unit) {
    require(target != null, "Missing argument 'target'.")

    val handler = new ThisFunction10[js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, Unit] {
      override def apply(t: js.Any, a0: js.Any, a1: js.Any, a2: js.Any, a3: js.Any, a4: js.Any,
        a5: js.Any, a6: js.Any, a7: js.Any, a8: js.Any, a9: js.Any) {

        bindTarget(t, target)

        target.inject(Seq(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9))
        target.initialize()
      }
    }

    val args = js.Array[js.Any]()

    target.dependencies.foreach(args.push(_))
    args.push(handler)

    handler.asInstanceOf[js.Dynamic].obj = target.asInstanceOf[js.Object]

    fn(args)
  }

  private[angularjs] def bindTarget(service: js.Any, target: InjectionTarget) {
    try {
      service.asInstanceOf[js.Dynamic]._serviceTarget = target.asInstanceOf[js.Object]
    } catch {
      case _: Throwable =>
    }
  }

  private[angularjs] def unbindTarget[A <: InjectionTarget](service: js.Any): Option[A] = {
    val target: UndefOr[Any] = service.asInstanceOf[js.Dynamic]._serviceTarget

    target.map(_.asInstanceOf[A]).toOption
  }
}

class ModuleProxy(val module: Module) {
  require(module != null, "Missing argument 'module'.")

  import Module.asService

  def factory(target: Factory[_]*): this.type = {
    require(target != null, "Missing argument 'target'.")

    def registerFactory(f: Factory[_]) {
      val handler = (a0: js.Any, a1: js.Any, a2: js.Any, a3: js.Any, a4: js.Any,
        a5: js.Any, a6: js.Any, a7: js.Any, a8: js.Any, a9: js.Any) => {
        f.inject(Seq(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9))
        f.initialize()
        f()
      }

      val args = js.Array[js.Any]()

      f.dependencies.foreach(args.push(_))
      args.push(handler)

      module.factory(f.name, args)
    }

    target.foreach(registerFactory(_))
    this
  }

  def config(target: InjectionTarget*): this.type = {
    require(target != null, "Missing argument 'target'.")

    target.foreach(t => asService(t) { args: js.Array[js.Any] => module.config(args) })
    this
  }

  def controller(target: Controller*): this.type = {
    require(target != null, "Missing argument 'target'.")

    target.foreach(t => asService(t) { args: js.Array[js.Any] => module.controller(t.name, args) })
    this
  }

  def run(target: InjectionTarget*): this.type = {
    require(target != null, "Missing argument 'target'.")

    target.foreach(t => asService(t) { args: js.Array[js.Any] => module.run(args) })
    this
  }

  def directive(directives: Directive*): this.type = {
    require(directives != null, "Missing argument 'directives'.")

    def registerDirective(d: Directive) {
      val handler = (a0: js.Any, a1: js.Any, a2: js.Any, a3: js.Any, a4: js.Any,
        a5: js.Any, a6: js.Any, a7: js.Any, a8: js.Any, a9: js.Any) => {
        d.inject(Seq(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9))
        d.initialize()
        d.buildConfig()
      }

      val args = js.Array[js.Any]()

      d.dependencies.foreach(args.push(_))
      args.push(handler)

      module.directive(d.name, args)
    }

    directives.foreach(registerDirective)
    this
  }
}
