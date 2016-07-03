package com.greencatsoft.angularjs

import java.lang.{Runnable => JRunnable}

import com.greencatsoft.angularjs.core.Timeout

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

class AngularExecutionContext(timeout: Timeout) extends ExecutionContextExecutor {

  def execute(runnable: JRunnable) {
    def run() = try {
      runnable.run ()
    } catch {
      case t: Throwable => reportFailure (t)
    }

    timeout (run _, 0)
  }

  def reportFailure(t: Throwable): Unit = t.printStackTrace ()
}

trait AngularExecutionContextProvider {

  def timeout: Timeout

  implicit val executionContext: ExecutionContext = new AngularExecutionContext (timeout)
}