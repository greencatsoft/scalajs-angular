package com.greencatsoft.angularjs

trait NamedTarget extends InjectionTarget {

  val name: String
}

abstract class AbstractNamedTarget(_name: String) extends NamedTarget {
  require(name != null, "Missing argument '_name'.")

  // use the name of the class/object if not specified explicitly
  override lazy val name: String =
    if (_name == null) this.getClass.getName.split("\\$").last
    else _name
}