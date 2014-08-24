package todomvc.example

import scala.scalajs.js.Any.fromArray
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import com.greencatsoft.angularjs.{ Module, angular }

/**
 * Call `todomvc.example.TodoApp().main()` from the HTML page to initialize and start the TodoMVC application.
 */
object TodoApp extends JSApp {

  override def main() {
    val app = angular.module("todomvc", Array.empty[String])

    // Module(app) returns a proxy for our module that provides the bridge for integration
    // between standard AngularJS and our typesafe + 'fluent' scalajs-angular bindings
    Module(app)
      .controller(TodoCtrl)
      .directive(EscapeDirective, FocusDirective)
  }
}
