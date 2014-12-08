package com.greencatsoft.angularjs.extensions.uirouter

import com.greencatsoft.angularjs.injectable
import scala.scalajs.js
import scala.scalajs.js.RegExp
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
  def when(whenPath: IUrlMatcher, hanlder: js.Function): UrlRouterProvider = ???
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

trait IUrlMatcher extends js.Object {
  def concat(pattern: String): IUrlMatcher = ???
  def exec(path: String, searchParams: js.Any): js.Any = ???
  def parameters(): js.Array[String] = ???
  def format(values: js.Any): String = ???
}