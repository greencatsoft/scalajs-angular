package com.greencatsoft.angularjs.q

import scala.concurrent.Future
import scala.scalajs.js
import scala.util.{ Failure, Success, Try }
import scala.language.implicitConversions

trait Defer extends js.Object {

  def resolve(value: js.Any): Unit = ???

  def reject(reason: String): Unit = ???

  def notify(value: js.Any): Unit = ???

  val promise: Promise = ???
}

object Defer {

  implicit def defer2promise[T](defer: Defer): scala.concurrent.Promise[T] = new DeferredPromise[T](defer)

  class DeferredPromise[T](defer: Defer) extends scala.concurrent.Promise[T] {

    private var completed = false

    override def future: Future[T] = new Promise.DeferredFuture(defer.promise)

    override def isCompleted: Boolean = completed

    override def tryComplete(result: Try[T]): Boolean = if (isCompleted) false else {
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
