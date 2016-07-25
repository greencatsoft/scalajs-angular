package com.greencatsoft.angularjs.extensions

import com.greencatsoft.angularjs.core.Promise
import com.greencatsoft.angularjs.injectable

import scala.scalajs.js
import scala.scalajs.js.{ Dictionary, UndefOr, | }

@js.native
@injectable("$translate")
trait Translate extends js.Object {

  def apply(translationId: String): Promise[String] = js.native

  def apply(translationId: js.Array[String]): Promise[Dictionary[String]] = js.native

  def apply(
    translationId: String,
    interpolateParams: Dictionary[Any]): Promise[String] = js.native

  def apply(
    translationId: js.Array[String],
    interpolateParams: Dictionary[Any]): Promise[Dictionary[String]] = js.native

  def apply(
    translationId: String,
    interpolateParams: Dictionary[Any],
    interpolationId: UndefOr[String]): Promise[String] = js.native

  def apply(
    translationId: js.Array[String],
    interpolateParams: Dictionary[Any],
    interpolationId: UndefOr[String]): Promise[Dictionary[String]] = js.native

  def preferredLanguage(): UndefOr[String | js.Array[String]] = js.native

  def preferredLanguage(langKey: String | js.Array[String]): Translate = js.native

  def cloakClassName(): String = js.native

  def nestedObjectDelimeter(): String = js.native

  def negotiateLocale(): UndefOr[String] = js.native

  def negotiateLocale(langKey: String): UndefOr[String] = js.native

  def fallbackLanguage(): String | js.Array[String] = js.native

  def fallbackLanguage(langKey: String | js.Array[String]): Translate = js.native

  def useFallbackLanguage(): Unit = js.native

  def useFallbackLanguage(langKey: String | Boolean): Unit = js.native

  def proposedLanguage(): UndefOr[String] = js.native

  def storage(): js.Object = js.native

  def storageKey(): String = js.native

  def use(): UndefOr[String] = js.native

  def use(langKey: String): Promise[js.Object] = js.native

  def isPostCompilingEnabled(): Boolean = js.native

  def isForceAsyncReloadEnabled(): Boolean = js.native

  def resolveClientLocale(): UndefOr[String] = js.native

  def refresh(langKey: String): Promise[Unit] = js.native

  def instant(translationId: String): Promise[String] = js.native

  def instant(translationId: js.Array[String]): Promise[Dictionary[String]] = js.native

  def instant(
    translationId: String,
    interpolateParams: Dictionary[Any]): Promise[String] = js.native

  def instant(
    translationId: js.Array[String],
    interpolateParams: Dictionary[Any]): Promise[Dictionary[String]] = js.native

  def instant(
    translationId: String,
    interpolateParams: Dictionary[Any],
    interpolationId: UndefOr[String]): Promise[String] = js.native

  def instant(
    translationId: js.Array[String],
    interpolateParams: Dictionary[Any],
    interpolationId: UndefOr[String]): Promise[Dictionary[String]] = js.native

  def loaderCache(): String | Boolean | js.Object = js.native

  def isReady(): Boolean = js.native

  def onReady(fn: js.Function0[Unit]): Promise[Unit] = js.native

  def versionInfo(): String = js.native

  def displayName: String = js.native
}

@js.native
@injectable("$translateProvider")
trait TranslateProvider extends js.Object {

  def cloakClassName(): String = js.native

  def cloakClassName(name: String): TranslateProvider = js.native

  def nestedObjectDelimeter(): String = js.native

  def nestedObjectDelimeter(delimiter: String): TranslateProvider = js.native

  def addInterpolation(factory: String): TranslateProvider = js.native

  def useMessageFormatInterpolation(): TranslateProvider = js.native

  def useInterpolation(factory: String): TranslateProvider = js.native

  def useSanitizeValueStrategy(value: String): TranslateProvider = js.native

  def preferredLanguage(): UndefOr[String | js.Array[String]] = js.native

  def preferredLanguage(langKey: String | js.Array[String]): TranslateProvider = js.native

  def translationNotFoundIndicator(indicator: String): TranslateProvider = js.native

  def translationNotFoundIndicatorLeft(): UndefOr[String] = js.native

  def translationNotFoundIndicatorLeft(indicator: String): TranslateProvider = js.native

  def translationNotFoundIndicatorRight(): UndefOr[String] = js.native

  def translationNotFoundIndicatorRight(indicator: String): TranslateProvider = js.native

  def fallbackLanguage(): String | js.Array[String] = js.native

  def fallbackLanguage(langKey: String | js.Array[String]): TranslateProvider = js.native

  def use(): UndefOr[String] = js.native

  def use(langKey: String): TranslateProvider = js.native

  def storageKey(): String = js.native

  def storageKey(key: String): TranslateProvider = js.native

  def useUrlLoader(url: String): TranslateProvider = js.native

  def useUrlLoader(url: String, options: Dictionary[Any]): TranslateProvider = js.native

  def useStaticFilesLoader(): TranslateProvider = js.native

  def useStaticFilesLoader(options: Dictionary[Any]): TranslateProvider = js.native

  def useLoader(loaderFactory: String): TranslateProvider = js.native

  def useLoader(loaderFactory: String, options: Dictionary[Any]): TranslateProvider = js.native

  def useLocalStorage(): TranslateProvider = js.native

  def useCookieStorage(): TranslateProvider = js.native

  def useStorage(storageFactory: String): TranslateProvider = js.native

  def storagePrefix(): UndefOr[String] = js.native

  def storagePrefix(prefix: String): TranslateProvider = js.native

  def useMissingTranslationHandlerLog(): TranslateProvider = js.native

  def useMissingTranslationHandlerLog(factory: String): TranslateProvider = js.native

  def usePostCompiling(value: Boolean): TranslateProvider = js.native

  def forceAsyncReload(value: Boolean): TranslateProvider = js.native

  def uniformLanguageTag(options: String): TranslateProvider = js.native

  def uniformLanguageTag(options: js.Object): TranslateProvider = js.native

  def determinePreferredLanguage(): TranslateProvider = js.native

  def determinePreferredLanguage(fn: js.Function0[String]): TranslateProvider = js.native

  def resolveClientLocale(): UndefOr[String] = js.native

  def registerAvailableLanguageKeys(): js.Array[String] = js.native

  def registerAvailableLanguageKeys(aliases: Dictionary[String]): js.Array[String] = js.native

  def registerAvailableLanguageKeys(languageKeys: js.Array[String]): TranslateProvider = js.native

  def registerAvailableLanguageKeys(languageKeys: js.Array[String], aliases: Dictionary[String]): TranslateProvider = js.native

  def useLoaderCache(cache: Boolean): TranslateProvider = js.native

  def useLoaderCache(cache: String): TranslateProvider = js.native

  def useLoaderCache(cache: js.Object): TranslateProvider = js.native

  def directivePriority(): Int = js.native

  def directivePriority(priority: Int): TranslateProvider = js.native

  def statefulFilter(): Boolean = js.native

  def statefulFilter(state: Boolean): TranslateProvider = js.native

  def translations(): js.Object = js.native

  def translations(langKey: String): js.Object = js.native

  def translations(langKey: String, translationTable: js.Object): TranslateProvider = js.native
}
