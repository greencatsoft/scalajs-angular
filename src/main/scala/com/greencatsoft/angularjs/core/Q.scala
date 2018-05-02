package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.concurrent.{ExecutionContext, Future}
import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.runtime.wrapJavaScriptException
import scala.util.{ Failure, Success, Try }

@js.native
@injectable("$q")
trait Q extends js.Object {

  def defer[T](): Defer[T] = js.native

  def reject[T](reason: js.Any): Promise[T] = js.native
}

@js.native
@injectable("$qProvider")
trait QProvider extends js.Object {

  def errorOnUnhandledRejections(): Boolean = js.native

  def errorOnUnhandledRejections(value: Boolean): QProvider = js.native
}

@js.native
trait Defer[T] extends js.Object {

  def resolve(value: T): Unit = js.native

  def reject(reason: js.Any): Unit = js.native

  def notify(value: T): Unit = js.native

  def promise: Promise[T] = js.native
}

object Defer {

  implicit class DeferredPromise[A](defer: Defer[A]) extends scala.concurrent.Promise[A] {
    private var completed = false

    override def future: Future[A] = Promise.promise2future(defer.promise)

    override def isCompleted: Boolean = completed

    override def tryComplete(result: Try[A]): Boolean = if (isCompleted) false else {
      result match {
        case Success(r) =>
          defer.resolve(r)
        case Failure(e) =>
          defer.reject(e.getMessage)
      }

      this.completed = true
      completed
    }
  }
}

@js.native
trait Promise[T] extends js.Object {

  def `then`(successCallback: js.Function1[T, T]): this.type = js.native

  def `then`(successCallback: js.Function1[T, T], errorCallback: js.Function1[Any, Unit]): this.type = js.native

  def `then`(successCallback: js.Function1[T, T], errorCallback: js.Function1[Any, Unit], notifyCallback: js.Function1[Any, Unit]): this.type = js.native

  def `catch`(errorCallback: js.Function1[Any, Unit]): this.type = js.native

  def `finally`(callback: js.Function1[Any, Unit]): Unit = js.native
}

object Promise {

  implicit def promise2future[A](promise: Promise[A]): Future[A] = {
    val p = concurrent.Promise[A]()

    def onSuccess(data: A): A = {
      p.success(data)
      data
    }

    def onError(error: Any): Unit = p.failure(wrapJavaScriptException(error))

    promise.`then`(onSuccess _).`catch`(onError _)

    p.future
  }

  implicit def future2promise[A](f: Future[A])(implicit ec: ExecutionContext, q: Q): Promise[A] = {
    val deferred = q.defer[A]()
    f.onComplete {
      case Success(a) =>
        deferred.resolve(a)
      case Failure(t) =>
        deferred.reject(t.getMessage)
    }
    deferred.promise
  }
}
