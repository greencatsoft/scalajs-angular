package com.greencatsoft.angularjs.extensions.uirouter

import com.greencatsoft.angularjs.injectable
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenMap
/**
 * PLEASE NOTE THAT THE FOLLOWING BINDINGS ARE FAR FROM BEING EXHAUSTIVE 
 *
 * The $stateProvider works similar to Angular's v1 router, but it focuses purely on state.
 *
 * A state corresponds to a "place" in the application in terms of the overall UI and navigation.
 * A state describes (via the controller / template / view properties) what the UI looks like and does at that place.
 * States often have things in common, and the primary way of factoring out these commonalities in this model is via the state hierarchy, i.e. parent/child states aka nested states.
 *
 *
 */
@injectable("$stateProvider")
trait StateProvider extends js.Object {
  def state(name: String, config: IState): StateProvider = ???
  def state(config: IState): StateProvider = ???
  def decorator(name: String = ???, decorator: js.Function2[IState, js.Function, Any] = ???): js.Dynamic = ???
}

trait IState extends js.Object {
  var name: String = ???
  var template: String = ???
  var templateUrl: String = ???
  var templateProvider: js.Any = ???
  var controller: js.Any = ???
  var controllerAs: String = ???
  var controllerProvider: js.Any = ???
  var url: String = ???
  var params: js.Array[js.Any] = ???
  var `abstract`: Boolean = ???
  var onEnter: js.Function = ???
  var onExit: js.Function = ???
  var data: js.Any = ???
  var views: js.Dictionary[IView] = ???
}

object State {
  def apply(url: String, isAbstract: Boolean = false, templateUrl: String = "", views: Map[String, IView] = Map.empty): IState = {
    val out = new js.Object().asInstanceOf[IState]
    out.url = url
    out.`abstract` = isAbstract
    if (!templateUrl.isEmpty()) out.templateUrl = templateUrl
    if (!views.isEmpty) out.views = views.toJSDictionary
    out
  }
}

trait IView extends js.Object {
  var templateUrl: String = ???
  var controller: String = ???
}

object View {
  def apply(templateUrl: String, controller: String): IView = {
    val out = new js.Object().asInstanceOf[IView]
    out.templateUrl = templateUrl
    out.controller = controller
    out
  }
}
