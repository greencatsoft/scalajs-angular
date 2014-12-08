package com.greencatsoft.angularjs.extensions.uirouter

import com.greencatsoft.angularjs.injectable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSBracketAccess

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
