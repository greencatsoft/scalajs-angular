package com.greencatsoft.angularjs.http

import scala.language.implicitConversions
import scala.concurrent.{ CanAwait, ExecutionContext, Future }
import scala.concurrent.duration.Duration
import scala.scalajs.js
import scala.scalajs.js.{ JavaScriptException, UndefOr }
import scala.scalajs.js.Any.fromFunction1
import scala.util.{ Failure, Success, Try }

import com.greencatsoft.angularjs.q.Promise

trait HttpPromise extends Promise {

  def success(callback: js.Function1[js.Any, Unit]): this.type

  def success(callback: js.Function2[js.Any, Int, Unit]): this.type

  def success(callback: js.Function3[js.Any, js.Any, Int, Unit]): this.type

  def success(callback: js.Function4[js.Any, Int, js.Any, js.Any, Unit]): this.type

  def success(callback: js.Function5[js.Any, Int, js.Any, js.Any, js.Any, Unit]): this.type

  def error(callback: js.Function1[js.Any, Unit]): this.type

  def error(callback: js.Function2[js.Any, Int, Unit]): this.type

  def error(callback: js.Function3[js.Any, js.Any, Int, Unit]): this.type

  def error(callback: js.Function4[js.Any, Int, js.Any, js.Any, Unit]): this.type

  def error(callback: js.Function5[js.Any, Int, js.Any, js.Any, UndefOr[String], Unit]): this.type
}

object HttpPromise {

  implicit def promise2future[T](promise: Promise): Future[T] = new HttpFuture[T](promise)

  trait HttpResult extends js.Object {

    val config: js.Any = ???

    val data: js.Any = ???

    val status: Int = ???

    val statusText: String = ???
  }

  class HttpFuture[T](promise: Promise) extends Future[T] {

    type Listener[U] = Try[T] => U

    private var result: Option[Try[T]] = None

    private var listeners: Seq[Listener[_]] = Seq.empty

    private def notify(result: Try[T]): Option[Try[T]] = {
      listeners.foreach(_(result))
      Some(result)
    }

    promise `then` { (r: js.Any) =>
      val httpResult = r.asInstanceOf[HttpResult]
      this.result = notify(Success(httpResult.data.asInstanceOf[T]))
      r
    } `catch` { (error: js.Any) =>
      val httpResult = error.asInstanceOf[HttpResult]
      this.result = notify(Failure(JavaScriptException(httpResult.data)))
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