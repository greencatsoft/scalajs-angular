package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.scope.ScopeAware

trait Controller extends NamedTarget with ScopeAware

abstract class AbstractController(name: String)
  extends AbstractNamedTarget(name) with Controller