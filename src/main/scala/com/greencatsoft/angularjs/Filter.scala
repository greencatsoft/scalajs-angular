package com.greencatsoft.angularjs

import scala.scalajs.js
import scala.scalajs.js.Any.fromFunction11
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops

trait Filter extends Factory[js.Function] {

  override def apply(): js.Function = (text: String, arg0: UndefOr[Any], arg1: UndefOr[Any], arg2: UndefOr[Any],
    arg3: UndefOr[Any], arg4: UndefOr[Any], arg5: UndefOr[Any], arg6: UndefOr[Any], arg7: UndefOr[Any],
    arg8: UndefOr[Any], arg9: UndefOr[Any]) =>
    filter(text, Seq(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9).map(_.toList).filterNot(_.isEmpty).map(_.head))

  def filter(text: String): String = text

  def filter(text: String, args: Seq[Any]): String = filter(text)
}
