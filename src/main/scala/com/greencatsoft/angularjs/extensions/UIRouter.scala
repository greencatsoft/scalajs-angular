package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.core.Promise
import com.greencatsoft.angularjs.{ Factory, ServiceDefinition, injectable }
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenMap
import scala.scalajs.js.annotation.{ JSBracketAccess, JSName }
import scala.scalajs.js.{ Dictionary, RegExp, UndefOr, | }

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
  * url: &#39;/users/:id/details/{type}/{repeat:[0-9]+}?from&to&#39;
 *
 * Then you navigated your browser to:
 *
  * &#39;/users/123/details//0&#39;
 *
 * Your \$stateParams object would be
 *
  * { id:&#39;123&#39;, type:&#39;&#39;, repeat:&#39;0&#39; }
 *
 * Then you navigated your browser to:
 *
  * &#39;/users/123/details/default/0?from=there&to=here&#39;
 *
 * Your \$stateParams object would be
 *
  * { id:&#39;123&#39;, type:&#39;default&#39;, repeat:&#39;0&#39;, from:&#39;there&#39;, to:&#39;here&#39; }
 */
@js.native
@injectable("$stateParams")
trait StateParams extends js.Object {
  /**
   * Get the parameter value from its name.<br/>
    * If you have a \$stateParams object { id:&#39;123&#39;, type:&#39;default&#39;, repeat:&#39;0&#39;, from:&#39;there&#39;, to:&#39;here&#39; },
   * then to get the value of the "type" key, you should call \$stateParams("type").
   */
  @JSBracketAccess
  def apply[A](key: String): UndefOr[A] = js.native
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = js.native
}

/**
  * The \$stateProvider works similar to Angular&#39;s v1 router, but it focuses purely on state.
 *
 * A state corresponds to a "place" in the application in terms of the overall UI and navigation.
 * A state describes (via the controller / template / view properties) what the UI looks like and does at that place.
 * States often have things in common, and the primary way of factoring out these commonalities in this model is via the state hierarchy, i.e. parent/child states aka nested states.
 *
 *
 */
@js.native
@injectable("$stateProvider")
trait StateProvider extends js.Object {
  def state(name: String, config: State): StateProvider = js.native
  def state(config: State): StateProvider = js.native
  def decorator(name: String = js.native, decorator: js.Function2[State, js.Function, Any] = js.native): js.Dynamic = js.native
}

@js.native
trait State extends js.Object {
  var name: String = js.native
  var template: String = js.native
  var templateUrl: String = js.native
  var templateProvider: js.Any = js.native
  var controller: js.Any = js.native
  var controllerAs: String = js.native
  var controllerProvider: js.Any = js.native
  var url: String = js.native
  var params: Dictionary[_] = js.native
  var parent: js.Any = js.native
  var resolve: Dictionary[js.Function0[_] | ServiceDefinition[Factory[_]]] = js.native
  var `abstract`: Boolean = js.native
  var onEnter: js.Function | ServiceDefinition[_] = js.native
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

@js.native
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
@js.native
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

@js.native
trait UrlMatcher extends js.Object {
  def concat(pattern: String): UrlMatcher = js.native
  def exec(path: String, searchParams: js.Any): js.Any = js.native
  def parameters(): js.Array[String] = js.native
  def format(values: js.Any): String = js.native
}

@js.native
@injectable("$state")
trait StateService extends js.Object {

  def current: UndefOr[State] = js.native

  def params: UndefOr[Dictionary[_]] = js.native

  def get(): js.Array[State] = js.native

  def get(stateOrName: String | State): UndefOr[State] = js.native

  def get(stateOrName: String | State, context: String | State): UndefOr[State] = js.native

  def go(to: String): Promise[State] = js.native

  def go(to: String, params: Dictionary[_] | StateParams): Promise[State] = js.native

  def go(to: String, params: Dictionary[_] | StateParams, options: TransitionOptions): Promise[State] = js.native

  def href(stateOrName: String | State): String = js.native

  def href(stateOrName: String | State, params: Dictionary[_] | StateParams): String = js.native

  def href(stateOrName: String | State, params: Dictionary[_] | StateParams, options: HrefOptions): String = js.native

  def includes(stateOrName: String | State): Boolean = js.native

  def includes(stateOrName: String | State, params: Dictionary[_] | StateParams): Boolean = js.native

  def includes(stateOrName: String | State, params: Dictionary[_] | StateParams, options: IncludeOptions): Boolean = js.native

  def is(stateOrName: String | State): Boolean = js.native

  def is(stateOrName: String | State, params: Dictionary[_] | StateParams): Boolean = js.native

  def is(stateOrName: String | State, params: Dictionary[_] | StateParams, options: IncludeOptions): Boolean = js.native

  def reload(state: js.Any): Promise[State] = js.native

  def transitionTo(to: String): Promise[State] = js.native

  def transitionTo(to: String, toParams: Dictionary[_] | StateParams): Promise[State] = js.native

  def transitionTo(to: String, toParams: Dictionary[_] | StateParams, options: TransitionOptions): Promise[State] = js.native
}

@js.native
trait TransitionOptions extends js.Object {
  var location: UndefOr[String | Boolean] = js.native
  var inherit: UndefOr[Boolean] = js.native
  var relative: UndefOr[js.Object] = js.native
  @JSName("notify") var broadcast: UndefOr[Boolean] = js.native
  var reload: UndefOr[Boolean] = js.native
}

object TransitionOptions {
  def apply(
    location: UndefOr[String | Boolean] = js.undefined,
    inherit: UndefOr[Boolean] = js.undefined,
    relative: UndefOr[js.Object] = js.undefined,
    broadcast: UndefOr[Boolean] = js.undefined,
    reload: UndefOr[Boolean] = js.undefined): TransitionOptions = {

    val options = (new js.Object).asInstanceOf[TransitionOptions]

    options.location = location
    options.inherit = inherit
    options.relative = relative
    options.broadcast = broadcast
    options.reload = reload

    options
  }
}

@js.native
trait HrefOptions extends js.Object {
  var lossy: UndefOr[Boolean] = js.native
  var inherit: UndefOr[Boolean] = js.native
  var relative: UndefOr[js.Object] = js.native
  var absolute: UndefOr[Boolean] = js.native
}

object HrefOptions {
  def apply(
    lossy: UndefOr[Boolean] = js.undefined,
    inherit: UndefOr[Boolean] = js.undefined,
    relative: UndefOr[js.Object] = js.undefined,
    absolute: UndefOr[Boolean] = js.undefined): HrefOptions = {

    val options = (new js.Object).asInstanceOf[HrefOptions]

    options.lossy = lossy
    options.inherit = inherit
    options.relative = relative
    options.absolute = absolute

    options
  }
}

@js.native
trait IncludeOptions extends js.Object {
  var relative: UndefOr[js.Object] = js.native
}

object IncludeOptions {
  def apply(relative: UndefOr[js.Object] = js.undefined): IncludeOptions = {

    val options = (new js.Object).asInstanceOf[IncludeOptions]

    options.relative = relative

    options
  }
}
