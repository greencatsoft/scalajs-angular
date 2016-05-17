package com.greencatsoft.angularjs.test

import com.greencatsoft.angularjs.core.Injector
import com.greencatsoft.angularjs.{Angular, Module, internal}

import scala.language.experimental.macros

/** Provides an injector for your test suites.
  *
  * Setup for example like this:
  * {{{
  *   class MyDirectiveSpec extends FunSpec with AngularTestEnvironment with ScopeOps with MustMatchers {
  *       override val app = Angular.module("app", Seq("ngAnimate", "ngMaterial")).directive[MyDirective]
  *       override val appModuleName = "app"
  *
  *       describe("MyDirective") {
  *         it("must render") {
  *           val scope = inject[RootScope].$new(true)
  *           scope.dynamic.greeting = "Hello World!"
  *
  *           val tag = """<my-directive greeting="{{greeting}}"></my-directive>"""
  *           val element = inject[Compile](tag)(scope, null)
  *           scope.$digest()
  *
  *           element.textContent must be ("Hello World!")
  *         }
  *       }
  *   }
  * }}}
  */
trait AngularTestEnvironment {
  /** Your angular module to be used during the test.
    *
    * For example {{{Angular.module("app", Seq("ngAnimate", "ngMaterial")).directive[MyDirective]}}}
    */
  val app: Module

  /** The name of your application module */
  val appModuleName: String

  /** Injector you can use in your tests to access services.
    *
    * You may want to use the `inject[A]` method for more readable code.
    */
  implicit lazy val injector: Injector = {
    val rootElement = org.scalajs.dom.document.documentElement
    Angular.bootstrap(rootElement, appModuleName)
  }

  /** Provides readable access to angular services.
    *
    * Example: {{{inject[RootScope].$new(true)}}}
    */
  def inject[A](implicit injector: Injector): A = macro internal.Injector.get[A]
}
