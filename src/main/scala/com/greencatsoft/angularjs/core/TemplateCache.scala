package com.greencatsoft.angularjs.core

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

/**
 * Template cache API.
 *
 * @see <a href="https://docs.angularjs.org/api/ng/service/\$templateCache">https://docs.angularjs.org/api/ng/service/\$templateCache</a>
 */
@injectable("$templateCache")
trait TemplateCache extends Cache {

  /**
   * Inserts a template into the cache.
   * 
   * @param id Template id.
   * @param tpl Template content.
   * @return The value stored.
   */
  def put(id: String, tpl: String): String = js.native
}
