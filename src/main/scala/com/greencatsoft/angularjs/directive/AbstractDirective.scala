package com.greencatsoft.angularjs.directive

import scala.scalajs.js

/**
 * Base class for AngularJS directives.
 *
 * @param _name         the name under which the directive will be registered to Angular.
 *                      Default: name of the class or object, stripped from the suffix 'Directive'
 * @param _restrict     String of subset "EACM" which restricts the directive to a specific declaration style.
 * @param _template     String with HTML markup that replaces the content of the directive's element
 * @param _templateUrl  Same as `_template`, but the template is loaded from the specified URL
 * @param _scope        If true, a new scope will be created for this directive.
 * @param _isolateScope If defined, a new "isolate" scope is defined; the provided Dictionary defines the set of
 *                      local properties that are derived from the parent scope
 * @param _compile     The compile function used to transform the template
 * @param _link        Only used if `_compile` is not defined; It is executed after the template has been cloned.
 *
 * @example {{{
 * // a directive named "foo" that replaces all instances of '<foo>...</foo>' with '<foo><bar>...</bar></foo>' :-)
 * object FooDirective extends AbstractDirective(
 *   _restrict = "E",
 *   _transclude = true,
 *   _template = "<bar ng-transclude></bar>"
 * )
 * }}}
 */
abstract class AbstractDirective(_name : String = null,
                                 _restrict : String = null,
                                 _transclude : Boolean = false,
                                 _template : String = null,
                                 _templateUrl : String = null,
                                 _scope: Boolean = false,
                                 _isolateScope: js.Dictionary[String] = null,
                                 _compile : js.Function = null,
                                 _link : js.Function = null)
  extends Directive {

  override lazy val name : String =
    // TODO: use getSimpleName when it is available
    if(_name==null) {
      val prefix = getClass.getName.split("\\.").last.split("\\$").last.replace("Directive", "")
      prefix.charAt(0).toLower + prefix.substring(1)
    }
    else _name

  override def restrict = _restrict
  override def transclude = _transclude
  override def template = _template
  override def templateUrl = _templateUrl
  override def scope = _scope
  override def isolateScope = _isolateScope
  override def compile = _compile
  override def link = _link
}
