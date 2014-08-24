package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.Any.{ fromFunction0, fromFunction1, fromString }
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops

import org.scalajs.dom.{ Element, HTMLElement, KeyboardEvent }

import com.greencatsoft.angularjs.directive.{ AttributeDirective, Attributes }
import com.greencatsoft.angularjs.timer.TimeoutAware

object EscapeDirective extends AttributeDirective {

  override val name = "todoEscape"

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
    val elem = elems.head.asInstanceOf[HTMLElement]

    elem.onkeydown = (event: KeyboardEvent) =>
      if (event.keyCode == 27) scope.$apply(attrs("todoEscape"))
  }
}

object FocusDirective extends AttributeDirective with TimeoutAware {

  override val name = "todoFocus"

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
    val elem = elems.head.asInstanceOf[HTMLElement]

    scope.$watch(attrs("todoFocus"),
      (newVal: UndefOr[js.Any]) => if (newVal.isDefined) timeout(() => elem.focus(), 0, false))
  }
}
