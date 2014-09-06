package com.greencatsoft.angularjs.controller

import com.greencatsoft.angularjs.scope.ScopeAware
import com.greencatsoft.angularjs.AbstractNamedTarget
import com.greencatsoft.angularjs.NamedTarget

trait Controller extends NamedTarget with ScopeAware

abstract class AbstractController(name: String)
  extends AbstractNamedTarget(name) with Controller
