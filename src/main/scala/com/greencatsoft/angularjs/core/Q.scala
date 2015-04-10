package com.greencatsoft.angularjs.core

import scala.concurrent.Future
import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.Any.fromFunction1
import scala.scalajs.runtime.wrapJavaScriptException
import scala.util.{ Failure, Success, Try }

import com.greencatsoft.angularjs.injectable

@injectable("$q")
trait Q extends js.Object {

  def defer(): Defer = js.native

  def promise(): Promise = js.native

  def reject(reason: js.Any): Promise = js.native
}

trait Defer extends js.Object {

  def resolve(value: js.Any): Unit = js.native

  def reject(reason: String): Unit = js.native

  def notify(value: js.Any): Unit = js.native

  val promise: Promise = js.native
}

object Defer {

  implicit def defer2promise[A](defer: Defer): scala.concurrent.Promise[A] = new DeferredPromise[A](defer)

  class DeferredPromise[A](defer: Defer) extends scala.concurrent.Promise[A] {

    private var completed = false

    override def future: Future[A] = Promise.promise2future(defer.promise)

    override def isCompleted: Boolean = completed

    override def tryComplete(result: Try[A]): Boolean = if (isCompleted) false else {
      result match {
        case Success(r) =>
          defer.resolve(r.asInstanceOf[js.Any])
        case Failure(e) =>
          defer.reject(e.getMessage())
      }

      this.completed = true
      completed
    }
  }
}

trait Promise extends js.Object {

  def `then`(successCallback: js.Function1[js.Any, js.Any]): this.type = js.native

  def `then`(successCallback: js.Function1[js.Any, js.Any], errorCallback: js.Function1[js.Any, Unit]): this.type = js.native

  def `then`(successCallback: js.Function1[js.Any, js.Any], errorCallback: js.Function1[js.Any, Unit], notifyCallback: js.Function1[js.Any, Unit]): this.type = js.native

  def `catch`(errorCallback: js.Function1[js.Any, Unit]): this.type = js.native

  def `finally`(callback: js.Function1[js.Any, Unit]): Unit = js.native
}

object Promise {

  implicit def promise2future[A](promise: Promise): Future[A] = {
    val p = concurrent.Promise[A]

    def onSuccess(data: js.Any): js.Any = {
      p.success(data.asInstanceOf[A])
      data
    }

    def onError(error: js.Any): Unit = p.failure(wrapJavaScriptException(error))

    promise.`then`(onSuccess _).`catch`(onError _)

    p.future
  }
}
