package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.Scope
import org.scalajs.dom
import org.scalajs.dom.Node
import org.scalajs.jquery.JQuery

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.UndefOr

@js.native
@injectable("$element")
trait AngularElement extends JQuery {
  def controller[A <: Controller[_ <: Scope]](): UndefOr[ServiceWrapper[A]] = js.native
  def controller[A <: Controller[_ <: Scope]](name: String): UndefOr[ServiceWrapper[A]] = js.native
}

object AngularElement {

  def apply(e: dom.Element): AngularElement = Angular.element(e)

  def apply(jQuery: JQuery): AngularElement = jQuery.asInstanceOf[AngularElement]

  implicit def jQueryToAngular(jQuery: JQuery): AngularElement = apply(jQuery)

  implicit class IterableAngularElement(val element: AngularElement) extends Seq[Node] {

    override def length: Int = element.length

    override def apply(idx: Int): Node = element.get(idx)

    override def iterator: Iterator[Node] = new Iterator[Node] {
      var index = 0

      def hasNext: scala.Boolean = index < length

      def next() = {
        val node = apply(index)
        index += 1
        node
      }
    }
  }
}
