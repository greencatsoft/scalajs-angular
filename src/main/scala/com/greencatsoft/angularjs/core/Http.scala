package com.greencatsoft.angularjs.core

import scala.concurrent.Future
import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.Any.{ fromFunction1, fromFunction5 }
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops
import scala.scalajs.js.annotation.JSExportAll

import com.greencatsoft.angularjs.Factory
import com.greencatsoft.angularjs.core.HttpStatus.int2HttpStatus
import com.greencatsoft.angularjs.injectable

@injectable("$http")
trait HttpService extends js.Object {

  def get(url: String): HttpPromise = js.native

  def get(url: String, config: HttpConfig): HttpPromise = js.native

  def head(url: String): HttpPromise = js.native

  def head(url: String, config: HttpConfig): HttpPromise = js.native

  def post(url: String): HttpPromise = js.native

  def post(url: String, data: js.Any): HttpPromise = js.native

  def post(url: String, data: js.Any, config: HttpConfig): HttpPromise = js.native

  def jsonp(url: String, config: HttpConfig): HttpPromise = js.native

  def put(url: String): HttpPromise = js.native

  def put(url: String, data: js.Any): HttpPromise = js.native

  def put(url: String, data: js.Any, config: HttpConfig): HttpPromise = js.native

  def delete(url: String): HttpPromise = js.native

  def delete(url: String, data: js.Any): HttpPromise = js.native

  def delete(url: String, data: js.Any, config: HttpConfig): HttpPromise = js.native
}

trait HttpConfig extends js.Object {

  var cache: Boolean = js.native

  var responseType: String = js.native

  var headers: js.Dictionary[String] = js.native

  var transformResponse: js.Array[js.Function2[js.Any, js.Any, js.Any]] = js.native

  var transformRequest: js.Array[js.Function2[js.Any, js.Any, js.Any]] = js.native
}

object HttpConfig {

  def empty: HttpConfig = {
    val config = new js.Object().asInstanceOf[HttpConfig]

    config.headers = js.Dictionary()
    config.transformRequest = js.Array()
    config.transformResponse = js.Array()

    config
  }

  def documentHandler: HttpConfig = {
    val config = empty

    config.responseType = "document"
    config
  }

  def postHandler: HttpConfig = {
    val config = empty

    config.headers = js.Dictionary(
      "method" -> "POST",
      "Content-Type" -> "application/x-www-form-urlencoded")
    config
  }
}

@injectable("$httpProvider")
trait HttpProvider extends js.Object {

  var defaults: HttpConfig = js.native

  var interceptors: js.Array[String] = js.native
}

trait HttpPromise extends Promise {

  def success(callback: js.Function1[js.Any, Unit]): this.type = js.native

  def success(callback: js.Function2[js.Any, Int, Unit]): this.type = js.native

  def success(callback: js.Function3[js.Any, js.Any, Int, Unit]): this.type = js.native

  def success(callback: js.Function4[js.Any, Int, js.Any, js.Any, Unit]): this.type = js.native

  def success(callback: js.Function5[js.Any, Int, js.Any, js.Any, js.Any, Unit]): this.type = js.native

  def error(callback: js.Function1[js.Any, Unit]): this.type = js.native

  def error(callback: js.Function2[js.Any, Int, Unit]): this.type = js.native

  def error(callback: js.Function3[js.Any, js.Any, Int, Unit]): this.type = js.native

  def error(callback: js.Function4[js.Any, Int, js.Any, js.Any, Unit]): this.type = js.native

  def error(callback: js.Function5[js.Any, Int, js.Any, js.Any, UndefOr[String], Unit]): this.type = js.native
}

trait HttpInterceptor {

  def q: Q

  def request(config: HttpConfig): HttpConfig = config

  def requestError(rejection: HttpResult): Promise = q.reject(rejection)

  def response(response: HttpResult): HttpResult = response

  def responseError(rejection: HttpResult): Promise = q.reject(rejection)
}

@JSExportAll
case class HttpInterceptorFunctions(
  request: js.Function1[HttpConfig, HttpConfig],
  requestError: js.Function1[HttpResult, Promise],
  response: js.Function1[HttpResult, HttpResult],
  responseError: js.Function1[HttpResult, Promise])

trait HttpInterceptorFactory extends Factory[HttpInterceptorFunctions] {

  implicit def toInterceptorFunctions(interceptor: HttpInterceptor): HttpInterceptorFunctions = {
    import interceptor._
    HttpInterceptorFunctions(request _, requestError _, response _, responseError _)
  }
}

case class HttpStatus(code: Int)

object HttpStatus {
  //From https://github.com/spray/spray/blob/master/spray-http/src/main/scala/spray/http/StatusCode.scala

  val Continue = HttpStatus(100)
  val SwitchingProtocols = HttpStatus(101)
  val Processing = HttpStatus(102)

  val Ok = HttpStatus(200)
  val Created = HttpStatus(201)
  val Accepted = HttpStatus(202)
  val NonAuthoritativeInformation = HttpStatus(203)
  val NoContent = HttpStatus(204)
  val ResetContent = HttpStatus(205)
  val PartialContent = HttpStatus(206)
  val MultiStatus = HttpStatus(207)
  val AlreadyReported = HttpStatus(208)
  val IMUsed = HttpStatus(226)

  val MultipleChoices = HttpStatus(300)
  val MovedPermanently = HttpStatus(301)
  val Found = HttpStatus(302)
  val SeeOther = HttpStatus(303)
  val NotModified = HttpStatus(304)
  val UseProxy = HttpStatus(305)
  val TemporaryRedirect = HttpStatus(307)
  val PermanentRedirect = HttpStatus(308)

  val BadRequest = HttpStatus(400)
  val Unauthorized = HttpStatus(401)
  val PaymentRequired = HttpStatus(402)
  val Forbidden = HttpStatus(403)
  val NotFound = HttpStatus(404)
  val MethodNotAllowed = HttpStatus(405)
  val NotAcceptable = HttpStatus(406)
  val ProxyAuthenticationRequired = HttpStatus(407)
  val RequestTimeout = HttpStatus(408)
  val Conflict = HttpStatus(409)
  val Gone = HttpStatus(410)
  val LengthRequired = HttpStatus(411)
  val PreconditionFailed = HttpStatus(412)
  val EntityTooLarge = HttpStatus(413)
  val RequestUriTooLong = HttpStatus(414)
  val UnsupportedMediaType = HttpStatus(415)
  val RequestedRangeNotSatisfiable = HttpStatus(416)
  val ExpectationFailed = HttpStatus(417)
  val EnhanceYourCalm = HttpStatus(420)
  val UnprocessableEntity = HttpStatus(422)
  val Locked = HttpStatus(423)
  val FailedDependency = HttpStatus(424)
  val UnorderedCollection = HttpStatus(425)
  val UpgradeRequired = HttpStatus(426)
  val PreconditionRequired = HttpStatus(428)
  val TooManyRequests = HttpStatus(429)
  val RequestHeaderFieldsTooLarge = HttpStatus(431)
  val RetryWith = HttpStatus(449)
  val BlockedByParentalControls = HttpStatus(450)
  val UnavailableForLegalReasons = HttpStatus(451)

  val InternalServerError = HttpStatus(500)
  val NotImplemented = HttpStatus(501)
  val BadGateway = HttpStatus(502)
  val ServiceUnavailable = HttpStatus(503)
  val GatewayTimeout = HttpStatus(504)
  val HTTPVersionNotSupported = HttpStatus(505)
  val VariantAlsoNegotiates = HttpStatus(506)
  val InsufficientStorage = HttpStatus(507)
  val LoopDetected = HttpStatus(508)
  val BandwidthLimitExceeded = HttpStatus(509)
  val NotExtended = HttpStatus(510)
  val NetworkAuthenticationRequired = HttpStatus(511)
  val NetworkReadTimeout = HttpStatus(598)
  val NetworkConnectTimeout = HttpStatus(599)

  implicit def int2HttpStatus(code: Int): HttpStatus = HttpStatus(code)
}

case class HttpException(status: HttpStatus, message: String) extends Exception

object HttpPromise {

  implicit def promise2future[A](promise: HttpPromise): Future[A] = {
    val p = concurrent.Promise[A]

    def onSuccess(data: js.Any): Unit = p.success(data.asInstanceOf[A])

    def onError(data: js.Any, status: Int, config: js.Any, headers: js.Any, statusText: UndefOr[String]): Unit =
      p failure HttpException(status, statusText getOrElse s"Failed to process HTTP request: '$data'")

    promise.success(onSuccess _).error(onError _)

    p.future
  }
}

trait HttpResult extends js.Object {

  val config: js.Any = js.native

  val data: js.Any = js.native

  val status: Int = js.native

  val statusText: String = js.native
}