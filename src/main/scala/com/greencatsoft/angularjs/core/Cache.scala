package com.greencatsoft.angularjs.core

import scala.scalajs.js
import scala.scalajs.js.UndefOr

import com.greencatsoft.angularjs.injectable

/**
 * Cache object used to store and retrieve data, primarily used by
 * <code>\$http</code> and the <code>script</code> directive to cache templates
 * and other data.
 *
 * @see <a href="https://docs.angularjs.org/api/ng/type/\$cacheFactory.Cache">https://docs.angularjs.org/api/ng/type/\$cacheFactory.Cache</a>
 */
@js.native
@injectable("$cache")
trait Cache extends js.Object {

  /**
   * Returns information about the cache.
   *
   * @return Object with the following properties:
   *  <ul>
   *    <li><code>id</code>: id of the cache instance</li>
   *    <li><code>size</code>: number of entries kept in the cache</li>
   *    <li>...: any additional properties from the options object when creating
   *        the cache.</li>
   *  </ul>
   */
  def info(): js.Dictionary[js.Any] = js.native

  /**
   * Inserts a named entry into the Cache and increments the size of the cache
   * if the key was not already present in the cache. If behaving like an LRU
   * cache, it will also remove stale entries from the set.
   * 
   * It will not insert undefined values into the cache.
   *
   * @param key The key under which the cached data is stored.
   * @param value The value to store alongside the key. If it is undefined, the
   *   key will not be stored.
   * @return The stored value.
   */
  def put[T <: js.Any](key: String, value: T): T = js.native

  /**
   * Retrieves named data stored in the Cache object.
   *
   * @param key The key of the data to be retrieved.
   * @return The value stored.
   */
  def get[T <: js.Any](key: String): UndefOr[T] = js.native

  /**
   * Removes an entry from the Cache object.
   *
   * @param key The key of the entry to be removed.
   */
  def remove(key: String): Unit = js.native

  /**
   * Clears the cache.
   */
  def removeAll(): Unit = js.native

  /**
   * Destroys the Cache object entirely, removing it from the
   * <code>\$cacheFactory</code> set.
   */
  def destroy(): Unit = js.native
}

/**
 * Factory that constructs Cache objects and gives access to them.
 *
 * @see <a href="https://docs.angularjs.org/api/ng/service/\$cacheFactory">https://docs.angularjs.org/api/ng/service/\$cacheFactory</a>
 */
@js.native
@injectable("$cacheFactory")
trait CacheFactory extends js.Object {

  /**
   * Creates a new Cache.
   *
   * @param cacheId Name or id of the newly created cache.
   * @param options [optional] Options object that specifies the cache behavior.
   *  Properties:
   *  <ul>
   *    <li><code>capacity</code> (Int) - turns the cache into an LRU cache.</li>
   *  </ul>
   * @return Newly created cache object.
   */
  def apply(cacheId: String, options: js.Object): Cache = js.native
}
