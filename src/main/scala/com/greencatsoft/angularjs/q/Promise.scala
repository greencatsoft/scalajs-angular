package com.greencatsoft.angularjs.q

import scala.concurrent.{ CanAwait, ExecutionContext, Future }
import scala.concurrent.duration.Duration
import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.Any.fromFunction1
import scala.scalajs.js.JavaScriptException
import scala.util.{ Failure, Success, Try }

trait Promise extends js.Object {

  def `then`(successCallback: js.Function1[js.Any, js.Any]): this.type = ???

  def `then`(successCallback: js.Function1[js.Any, js.Any], errorCallback: js.Function1[js.Any, Unit]): this.type = ???

  def `then`(successCallback: js.Function1[js.Any, js.Any], errorCallback: js.Function1[js.Any, Unit], notifyCallback: js.Function1[js.Any, Unit]): this.type = ???

  def `catch`(errorCallback: js.Function1[js.Any, Unit]): this.type = ???

  def `finally`(callback: js.Function1[js.Any, Unit]): Unit = ???
}

object Promise {

  implicit def promise2future[T](promise: Promise): Future[T] = new DeferredFuture[T](promise)

  class DeferredFuture[T](promise: Promise) extends Future[T] {

    type Listener[U] = Try[T] => U

    private var result: Option[Try[T]] = None

    private var listeners: Seq[Listener[_]] = Seq.empty

    private def notify(result: Try[T]): Option[Try[T]] = {
      listeners.foreach(_(result))
      Some(result)
    }

    promise `then` { (r: js.Any) =>
      this.result = notify(Success(r.asInstanceOf[T]))
      r
    } `catch` { (error: js.Any) =>
      this.result = notify(Failure(JavaScriptException(error)))
    }

    override def ready(atMost: Duration)(implicit permit: CanAwait): this.type =
      throw new UnsupportedOperationException

    override def result(atMost: Duration)(implicit permit: CanAwait): T =
      throw new UnsupportedOperationException

    override def isCompleted: Boolean = result.isDefined

    override def onComplete[U](f: Listener[U])(implicit executor: ExecutionContext): Unit =
      listeners +:= f

    override def value: Option[Try[T]] = result
  }
}