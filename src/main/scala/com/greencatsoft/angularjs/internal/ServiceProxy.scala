package com.greencatsoft.angularjs.internal

import com.greencatsoft.angularjs.{ Service, ServiceDefinition, ServiceWrapper, injectable }

import scala.reflect.macros.{blackbox => b}
import scala.scalajs.js
import scala.scalajs.js.UndefOr

object ServiceProxy {

  def bind[A <: Service](wrapper: ServiceWrapper[A], target: A) {
    try {
      wrapper.asInstanceOf[js.Dynamic].service = target.asInstanceOf[js.Object]
    } catch {
      case _: Throwable =>
    }
  }

  def unbind[A <: Service](wrapper: js.Object): Option[ServiceWrapper[A]] = {
    val target: UndefOr[Any] = wrapper.asInstanceOf[js.Dynamic].service

    target.toOption.filter(_.isInstanceOf[Service]) map {
      _ => wrapper.asInstanceOf[ServiceWrapper[A]]
    }
  }

  def identifier[A <: Service](c: b.Context)(implicit tag: c.WeakTypeTag[A]): Option[String] =
    identifierFromType(c)(tag.tpe)

  def identifierFromType(c: b.Context)(tpe: c.universe.Type): Option[String] = {
    import c.universe._

    def find(hierarchy: List[Symbol]): Option[String] = hierarchy match {
      case head :: tail =>
        val arg = head.typeSignature.typeSymbol.annotations.map(_.tree) collectFirst {
          case a if a.tpe =:= typeOf[injectable] =>
            a.children.tail
        }

        val value = arg collectFirst {
          case List(Literal(Constant(literal: String))) => literal
        }

        value match {
          case Some(s) => Some(s)
          case _ => find(tail)
        }
      case _ =>
        None
    }

    find(tpe.baseClasses)
  }

  def variableDependencies[A <: Service](c: b.Context)(implicit tag: c.WeakTypeTag[A]): Iterable[(String, c.universe.TypeSymbol, c.universe.MethodSymbol, Seq[c.universe.Type])] = {
    import c.universe._

    //    Should check if @inject annotation is present:
    //
    //    val members = tag.tpe.members filter {
    //      _.annotations.exists(_.tree.tpe =:= typeOf[inject])
    //    } collect {
    //      case m: MethodSymbol if m.isSetter => m
    //    }

    val setters = tag.tpe.members collect {
      case m: MethodSymbol if m.isSetter => m
    }

    val deps = setters map { s =>
      val tpe = s.paramLists.head.head.typeSignature
      val typeArgs = tpe.typeArgs
      val name = identifierFromType(c)(tpe.typeSymbol.typeSignature)

      name.toSeq map {
        (_, tpe.typeSymbol.asType, s, typeArgs)
      }
    }

    deps.flatten
  }

  def constantDependencies[A <: Service](c: b.Context)(implicit tag: c.WeakTypeTag[A]): Iterable[(String, c.universe.TypeSymbol, Seq[c.universe.Type])] = {
    import c.universe._

    val methods = tag.tpe.members collectFirst {
      case m: MethodSymbol if m.isPrimaryConstructor => m
    }

    val ctor = methods getOrElse {
      c.abort(c.enclosingPosition, s"The specified type '${tag.tpe}' does not have a suitable constructor.")
    }

    val arguments = ctor.paramLists.head map { arg =>
      (arg.name.toString, arg.typeSignature.dealias.typeSymbol.asType, arg.typeSignature.typeArgs)
    }

    arguments map {
      case (n, tpe, typeArgs) =>
        val name = identifierFromType(c)(tpe.typeSignature) getOrElse n

        (name, tpe.asType, typeArgs)
    }
  }

  def definitionFromObject[A <: Service](c: b.Context)(target: c.Expr[A])(implicit tag: c.WeakTypeTag[A]): c.Expr[ServiceDefinition[A]] = {
    newInstance [A](c)(Some (target))
  }

  def definitionFromClass[A <: Service](c: b.Context)(implicit tag: c.WeakTypeTag[A]): c.Expr[ServiceDefinition[A]] = {
    newInstance [A](c)(None)
  }

  private def newInstance[A <: Service](c: b.Context)(target: Option[c.Expr[A]])(implicit tag: c.WeakTypeTag[A]): c.Expr[ServiceDefinition[A]] = {
    import c.universe._

    val (instantiation, constDeps, constDepNames) = target match {
      case Some(t) => (q"$t", Nil, Nil)
      case None =>
        val deps = constantDependencies[A](c)
        val depNames = deps collect {
          case (name, _, _) => name
        }

        val args = deps.zipWithIndex collect {
          case ((_, argType, typeArgs), index) =>
            val argument = Ident(TermName(s"a$index"))

            if (argType.typeSignature.takesTypeArgs) {
              q"$argument.asInstanceOf[$argType[..$typeArgs]]"
            } else {
              q"$argument.asInstanceOf[$argType]"
            }
        }

        val expr = Apply(Select(
          New(Ident(tag.tpe.typeSymbol)), termNames.CONSTRUCTOR), args.toList)

        (expr, deps, depNames)
    }

    val varDeps = variableDependencies[A](c)
    val varDepNames = varDeps collect {
      case (name, _, _, _) => name
    }

    val offset = constDeps.size

    val assignments = varDeps.zipWithIndex collect {
      case ((_, argType, setter, typeArgs), index) =>
        val argument = Ident(TermName(s"a${index + offset}"))

        val value = if (argType.typeSignature.takesTypeArgs) {
          q"$argument.asInstanceOf[$argType[..$typeArgs]]"
        } else {
          q"$argument.asInstanceOf[$argType]"
        }

        Apply(Select(q"target", setter.asTerm), List(value))
    }

    val depNames = constDepNames ++ varDepNames

    val proxy = q"""{
      import scala.scalajs.js
      import scala.scalajs.js.UndefOr
      import scala.scalajs.js.JSConverters.JSRichOption

      import com.greencatsoft.angularjs.{ Initializable, Service, ServiceDefinition, ServiceWrapper }
      import com.greencatsoft.angularjs.internal.ServiceProxy

      val handler: js.ThisFunction20[js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, 
        js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, js.Any, UndefOr[js.Any]] = 
        (t: js.Any, a0: js.Any, a1: js.Any, a2: js.Any, a3: js.Any, a4: js.Any, a5: js.Any, a6: js.Any, a7: js.Any, a8: js.Any, a9: js.Any, 
        a10: js.Any, a11: js.Any, a12: js.Any, a13: js.Any, a14: js.Any, a15: js.Any, a16: js.Any, a17: js.Any, a18: js.Any, a19: js.Any) => {

        val target: Service = { ..$instantiation }

        ServiceProxy.bind(t.asInstanceOf[ServiceWrapper[Service]], target)

        ..$assignments

        target.initialize()

        val result = target match {
          case configurable: Function0[_] => configurable()
          case _ => null
        }

        Option(result).map(_.asInstanceOf[js.Any]).orUndefined
      }

      val proxy = js.Array[js.Any](..$depNames)

      proxy.push(handler)
      proxy.asInstanceOf[ServiceDefinition[${tag.tpe}]]
    }"""

    c.Expr[ServiceDefinition[A]](proxy)
  }
}