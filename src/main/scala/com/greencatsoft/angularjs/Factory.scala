package com.greencatsoft.angularjs

trait Factory[A] extends NamedTarget with Function0[A] {

  override def initialize(): Unit = Unit
}