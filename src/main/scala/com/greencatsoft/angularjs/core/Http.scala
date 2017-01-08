package com.greencatsoft.angularjs.core

import scala.concurrent.Future
import scala.language.implicitConversions

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExportAll
import scala.scalajs.js.{ Dictionary, UndefOr, | }

import com.greencatsoft.angularjs.core.HttpConfig.HeadersGetter
import com.greencatsoft.angularjs.core.HttpStatus.int2HttpStatus
import com.greencatsoft.angularjs.{ Factory, injectable }

@js.native
@injectable("$http")
trait HttpService extends js.Function1[HttpConfig, Any] {

  def defaults: HttpConfig = js.native

  def get[T](url: String): HttpPromise[T] = js.native

  def get[T](url: String, config: HttpConfig): HttpPromise[T] = js.native

  def head[T](url: String): HttpPromise[T] = js.native

  def head[T](url: String, config: HttpConfig): HttpPromise[T] = js.native

  def post[T](url: String): HttpPromise[T] = js.native

  def post[T](url: String, data: Any): HttpPromise[T] = js.native

  def post[T](url: String, data: Any, config: HttpConfig): HttpPromise[T] = js.native

  def jsonp[T](url: String, config: HttpConfig): HttpPromise[T] = js.native

  def put[T](url: String): HttpPromise[T] = js.native

  def put[T](url: String, data: Any): HttpPromise[T] = js.native

  def put[T](url: String, data: Any, config: HttpConfig): HttpPromise[T] = js.native

  def delete[T](url: String): HttpPromise[T] = js.native

  def delete[T](url: String, config: HttpConfig): HttpPromise[T] = js.native
}

@js.native
trait HttpConfig extends js.Object {

  var url: String = js.native

  var params: Dictionary[Any] = js.native

  var method: String = js.native

  var timeout: Int | Promise[_] = js.native

  var withCredentials: Boolean = js.native

  var cache: js.Any = js.native

  var responseType: String = js.native

  var xsrfCookieName: String = js.native

  var xsrfHeaderName: String = js.native

  var paramSerializer: String | js.Function1[Any, String] = js.native

  var jsonpCallbackParam: String = js.native

  var headers: Dictionary[String] | js.Function1[String, Dictionary[String]] = js.native

  var transformResponse: js.Array[js.Function3[Any, HeadersGetter | js.Array[HeadersGetter], Int, Any]] = js.native

  var transformRequest: js.Array[js.Function2[Any, HeadersGetter | js.Array[HeadersGetter], Any]] = js.native
}

object HttpConfig {

  type HeadersGetter = js.Function1[String, String]

  def apply(config: HttpConfig = HttpConfig.empty): HttpConfigBuilder = {
    new HttpConfigBuilder(config)
  }

  def empty: HttpConfig = {
    val config = new js.Object().asInstanceOf[HttpConfig]

    config.headers = Dictionary[String]()
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

    config.headers = Dictionary(
      "method" -> "POST",
      "Content-Type" -> "application/x-www-form-urlencoded")
    config
  }
}

class HttpConfigBuilder(init: HttpConfig) {

  private val config: HttpConfig = {
    val obj = new js.Object().asInstanceOf[HttpConfig]

    val source = init.asInstanceOf[js.Dynamic]
    val target = obj.asInstanceOf[js.Dynamic]

    js.Object.keys(init) collect {
      case key if init.hasOwnProperty(key) =>
        target.updateDynamic(key)(source.selectDynamic(key))
    }

    obj
  }

  def build: HttpConfig = config

  def url(url: String): HttpConfigBuilder = {
    config.url = url
    this
  }

  def params(params: Dictionary[Any]): HttpConfigBuilder = {
    config.params = params
    this
  }

  def method(method: String): HttpConfigBuilder = {
    config.method = method
    this
  }

  def timeout(timeout: Int | Promise[_]): HttpConfigBuilder = {
    config.timeout = timeout
    this
  }

  def withCredentials(withCredentials: Boolean): HttpConfigBuilder = {
    config.withCredentials = withCredentials
    this
  }

  def cache(cache: js.Any): HttpConfigBuilder = {
    config.cache = cache
    this
  }

  def responseType(responseType: String): HttpConfigBuilder = {
    config.responseType = responseType
    this
  }

  def xsrfCookieName(xsrfCookieName: String): HttpConfigBuilder = {
    config.xsrfCookieName = xsrfCookieName
    this
  }

  def xsrfHeaderName(xsrfHeaderName: String): HttpConfigBuilder = {
    config.xsrfHeaderName = xsrfHeaderName
    this
  }

  def paramSerializer(paramSerializer: String | js.Function1[Any, String]): HttpConfigBuilder = {
    config.paramSerializer = paramSerializer
    this
  }

  def jsonpCallbackParam(jsonpCallbackParam: String): HttpConfigBuilder = {
    config.jsonpCallbackParam = jsonpCallbackParam
    this
  }

  def headers(headers: Dictionary[String] | js.Function1[String, Dictionary[String]]): HttpConfigBuilder = {
    config.headers = headers
    this
  }

  def transformResponse(transformResponse: js.Function3[Any, HeadersGetter | js.Array[HeadersGetter], Int, Any]*): HttpConfigBuilder = {
    config.transformResponse = transformResponse.toJSArray
    this
  }

  def transformRequest(transformRequest: js.Function2[Any, HeadersGetter | js.Array[HeadersGetter], Any]*): HttpConfigBuilder = {
    config.transformRequest = transformRequest.toJSArray
    this
  }
}

@js.native
@injectable("$httpProvider")
trait HttpProvider extends js.Object {
  var defaults: HttpConfig = js.native
  var interceptors: js.Array[String] = js.native
}

trait HttpInterceptor {

  def q: Q

  def request(config: HttpConfig): HttpConfig = config

  def requestError[T](rejection: HttpResult[_]): Promise[T] = q.reject(rejection)

  def response(response: HttpResult[_]): HttpResult[_] = response

  def responseError[T](rejection: HttpResult[_]): Promise[T] = q.reject(rejection)
}

@JSExportAll
case class HttpInterceptorFunctions(
  request: js.Function1[HttpConfig, HttpConfig],
  requestError: js.Function1[HttpResult[_], Promise[_]],
  response: js.Function1[HttpResult[_], HttpResult[_]],
  responseError: js.Function1[HttpResult[_], Promise[_]])

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

@js.native
trait HttpPromise[T] extends Promise[HttpResult[T]]

object HttpPromise {
  implicit def promise2future[A](promise: HttpPromise[A]): Future[A] = {
    val p = concurrent.Promise[A]

    def onSuccess(arg: HttpResult[A]): HttpResult[A] = {
      p.success(arg.data)
      arg
    }

    def onError(arg: Any): Unit = {
      val data = arg.asInstanceOf[HttpResult[String]]
      p failure HttpException(data.status, data.statusText getOrElse s"Failed to process HTTP request: '${ data.data }'")
    }

    promise.`then`(onSuccess _).`catch`(onError _)

    p.future
  }
}

@js.native
trait HttpResult[T] extends js.Object {

  val config: HttpConfig = js.native

  val data: T = js.native

  val status: Int = js.native

  val statusText: UndefOr[String] = js.native

  val headers: js.Function1[String, String] = js.native
}
