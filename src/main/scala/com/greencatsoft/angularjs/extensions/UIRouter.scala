package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.injectable
import scala.scalajs.js.annotation.JSBracketAccess
import com.greencatsoft.angularjs.injectable
import scala.scalajs.js
import scala.scalajs.js.RegExp
import js.JSConverters._

/**
 * Wrappers for the uirouter module.
 * @see https://github.com/angular-ui/ui-router
 * PLEASE NOTE THAT THE FOLLOWING BINDINGS ARE FAR FROM BEING EXHAUSTIVE
 */

/**
 * The $stateParams service is an object that will have one key per url parameter.
 * The $stateParams is a perfect way to provide your controllers or other services with
 * the individual parts of the navigated url.<br/>
 * If you had a url on your state of:
 *
 * url: '/users/:id/details/{type}/{repeat:[0-9]+}?from&to'
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details//0'
 *
 * Your $stateParams object would be
 *
 * { id:'123', type:'', repeat:'0' }
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details/default/0?from=there&to=here'
 *
 * Your $stateParams object would be
 *
 * { id:'123', type:'default', repeat:'0', from:'there', to:'here' }
 */
@injectable("$stateParams")
trait StateParams extends js.Object {
  /**
   * Get the parameter value from its name.<br/>
   * If you have a $stateParams object { id:'123', type:'default', repeat:'0', from:'there', to:'here' },
   * then to get the value of the "type" key, you should call $stateParams("type").
   */
  @JSBracketAccess
  def apply(key: String): js.Any = ???
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = ???
}

/**
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
  def state(name: String, config: State): StateProvider = ???
  def state(config: State): StateProvider = ???
  def decorator(name: String = ???, decorator: js.Function2[State, js.Function, Any] = ???): js.Dynamic = ???
}

trait State extends js.Object {
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
  var views: js.Dictionary[View] = ???
}

object State {
  def apply(url: String, isAbstract: Boolean = false, templateUrl: String = "", views: Map[String, View] = Map.empty): State = {
    val out = new js.Object().asInstanceOf[State]
    out.url = url
    out.`abstract` = isAbstract
    if (!templateUrl.isEmpty()) out.templateUrl = templateUrl
    if (!views.isEmpty) out.views = views.toJSDictionary
    out
  }
}

trait View extends js.Object {
  var templateUrl: String = ???
  var controller: String = ???
}

object View {
  def apply(templateUrl: String, controller: String): View = {
    val out = new js.Object().asInstanceOf[View]
    out.templateUrl = templateUrl
    out.controller = controller
    out
  }
}

/**
 * $urlRouterProvider has the responsibility of watching $location.
 * When $location changes it runs through a list of rules one by one until a match is found.
 * $urlRouterProvider is used behind the scenes anytime you specify a url in a state configuration.
 * All urls are compiled into a UrlMatcher object.
 */
@injectable("$urlRouterProvider")
trait UrlRouterProvider extends js.Object {
  def when(whenPath: RegExp, handler: js.Function): UrlRouterProvider = ???
  def when(whenPath: RegExp, toPath: String): UrlRouterProvider = ???
  def when(whenPath: UrlMatcher, hanlder: js.Function): UrlRouterProvider = ???
  /**
   * Defines a path that is used when an invalid route is requested.
   * The function rule that returns the url path. The function is passed
   * two params: $injector and $location services, and must return a url string.
   */
  def otherwise(handler: js.Function): UrlRouterProvider = ???
  /**
   * Defines a path that is used when an invalid route is requested.
   * $path : The url path you want to redirect to
   */
  def otherwise(path: String): UrlRouterProvider = ???
  def rule(handler: js.Function): UrlRouterProvider = ???

}

trait UrlMatcher extends js.Object {
  def concat(pattern: String): UrlMatcher = ???
  def exec(path: String, searchParams: js.Any): js.Any = ???
  def parameters(): js.Array[String] = ???
  def format(values: js.Any): String = ???
}