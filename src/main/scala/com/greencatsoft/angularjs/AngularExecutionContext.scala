package com.greencatsoft.angularjs

import java.lang.{ Runnable => JRunnable }

import scala.concurrent.{ ExecutionContext, ExecutionContextExecutor }

import scala.scalajs.js
import scala.scalajs.js.{ JavaScriptException, undefined }

import com.greencatsoft.angularjs.core.{ ExceptionHandler, Timeout }

class AngularExecutionContext(
  timeout: Timeout,
  exceptionHandler: ExceptionHandler) extends ExecutionContextExecutor {

  def execute(runnable: JRunnable) {
    def run() = try {
      runnable.run()
    } catch {
      case t: Throwable => reportFailure(t)
    }

    timeout(() => run(), 0)
  }

  def reportFailure(t: Throwable) {
    t match {
      case t: JavaScriptException if t.exception.isInstanceOf[js.Error] =>
        exceptionHandler(t.exception.asInstanceOf[js.Error], undefined)
      case _ =>
        t.printStackTrace()
        exceptionHandler(js.Error(t.getMessage), undefined)
    }
  }
}

trait AngularExecutionContextProvider {

  def timeout: Timeout

  def exceptionHandler: ExceptionHandler

  implicit val executionContext: ExecutionContext = {
    new AngularExecutionContext(timeout, exceptionHandler)
  }
}
