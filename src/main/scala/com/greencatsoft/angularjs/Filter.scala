package com.greencatsoft.angularjs

import scala.scalajs.js
import scala.scalajs.js.Any.fromFunction11
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops

trait Filter[A] extends Factory[js.Function] {

  override def apply(): js.Function = (item: A, arg0: UndefOr[Any], arg1: UndefOr[Any], arg2: UndefOr[Any],
    arg3: UndefOr[Any], arg4: UndefOr[Any], arg5: UndefOr[Any], arg6: UndefOr[Any], arg7: UndefOr[Any],
    arg8: UndefOr[Any], arg9: UndefOr[Any]) =>
    filter(item, Seq(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9).map(_.toList).filterNot(_.isEmpty).map(_.head))

  def filter(item: A): Any = item

  def filter(item: A, args: Seq[Any]): Any = filter(item)
}

@injectable("$filter")
trait FilterService extends js.Function1[String, js.Function] {
  def apply(name: String): js.Function = js.native
}