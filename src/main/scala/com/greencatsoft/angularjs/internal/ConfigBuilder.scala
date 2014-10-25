package com.greencatsoft.angularjs.internal

import scala.scalajs.js

private[angularjs] trait ConfigBuilder {

  def buildConfig(config: Configuration = js.Dictionary.empty): Configuration = config
}
