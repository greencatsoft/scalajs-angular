package com.greencatsoft.angularjs.extensions

import scala.scalajs.js
import scala.scalajs.js.annotation.JSBracketAccess
import scala.scalajs.js.JSConverters.JSRichGenMap
import scala.scalajs.js.RegExp

import com.greencatsoft.angularjs.injectable

/**
 * Wrappers for the uirouter module.
 * @see https://github.com/angular-ui/ui-router
 * PLEASE NOTE THAT THE FOLLOWING BINDINGS ARE FAR FROM BEING EXHAUSTIVE
 */

/**
 * The \$stateParams service is an object that will have one key per url parameter.
 * The \$stateParams is a perfect way to provide your controllers or other services with
 * the individual parts of the navigated url.<br/>
 * If you had a url on your state of:
 *
 * url: '/users/:id/details/{type}/{repeat:[0-9]+}?from&to'
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details//0'
 *
 * Your \$stateParams object would be
 *
 * { id:'123', type:'', repeat:'0' }
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details/default/0?from=there&to=here'
 *
 * Your \$stateParams object would be
 *
 * { id:'123', type:'default', repeat:'0', from:'there', to:'here' }
 */
@injectable("$stateParams")
trait StateParams extends js.Object {
  /**
   * Get the parameter value from its name.<br/>
   * If you have a \$stateParams object { id:'123', type:'default', repeat:'0', from:'there', to:'here' },
   * then to get the value of the "type" key, you should call \$stateParams("type").
   */
  @JSBracketAccess
  def apply(key: String): js.Any = js.native
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = js.native
}

/**
 * The \$stateProvider works similar to Angular's v1 router, but it focuses purely on state.
 *
 * A state corresponds to a "place" in the application in terms of the overall UI and navigation.
 * A state describes (via the controller / template / view properties) what the UI looks like and does at that place.
 * States often have things in common, and the primary way of factoring out these commonalities in this model is via the state hierarchy, i.e. parent/child states aka nested states.
 *
 *
 */
@injectable("$stateProvider")
trait StateProvider extends js.Object {
  def state(name: String, config: State): StateProvider = js.native
  def state(config: State): StateProvider = js.native
  def decorator(name: String = js.native, decorator: js.Function2[State, js.Function, Any] = js.native): js.Dynamic = js.native
}

trait State extends js.Object {
  var name: String = js.native
  var template: String = js.native
  var templateUrl: String = js.native
  var templateProvider: js.Any = js.native
  var controller: js.Any = js.native
  var controllerAs: String = js.native
  var controllerProvider: js.Any = js.native
  var url: String = js.native
  var params: js.Dictionary[js.Any] = js.native
  var parent: js.Any = js.native
  var resolve: js.Function = js.native
  var `abstract`: Boolean = js.native
  var onEnter: js.Function = js.native
  var onExit: js.Function = js.native
  var data: js.Any = js.native
  var views: js.Dictionary[View] = js.native
}

object State {
  def apply(url: String, isAbstract: Boolean = false, templateUrl: String = "", views: Map[String, View] = Map.empty): State = {
    val out = new js.Object().asInstanceOf[State]
    out.url = url
    out.`abstract` = isAbstract
    if (templateUrl.nonEmpty) out.templateUrl = templateUrl
    if (views.nonEmpty) out.views = views.toJSDictionary
    out
  }
}

trait View extends js.Object {
  var templateUrl: String = js.native
  var controller: String = js.native
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
 * \$urlRouterProvider has the responsibility of watching \$location.
 * When \$location changes it runs through a list of rules one by one until a match is found.
 * \$urlRouterProvider is used behind the scenes anytime you specify a url in a state configuration.
 * All urls are compiled into a UrlMatcher object.
 */
@injectable("$urlRouterProvider")
trait UrlRouterProvider extends js.Object {
  def when(whenPath: RegExp, handler: js.Function): UrlRouterProvider = js.native
  def when(whenPath: RegExp, toPath: String): UrlRouterProvider = js.native
  def when(whenPath: UrlMatcher, handler: js.Function): UrlRouterProvider = js.native
  /**
   * Defines a path that is used when an invalid route is requested.
   * The function rule that returns the url path. The function is passed
   * two params: \$injector and \$location services, and must return a url string.
   */
  def otherwise(handler: js.Function): UrlRouterProvider = js.native
  /**
   * Defines a path that is used when an invalid route is requested.
   * \$path : The url path you want to redirect to
   */
  def otherwise(path: String): UrlRouterProvider = js.native
  def rule(handler: js.Function): UrlRouterProvider = js.native

}

trait UrlMatcher extends js.Object {
  def concat(pattern: String): UrlMatcher = js.native
  def exec(path: String, searchParams: js.Any): js.Any = js.native
  def parameters(): js.Array[String] = js.native
  def format(values: js.Any): String = js.native
}
