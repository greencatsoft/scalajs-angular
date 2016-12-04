package com.greencatsoft.angularjs

trait Factory[+A] extends Service with (() => A)
