AngularJS Binding for Scala.js
================================

## Introduction

**scalajs-angular** aims to help developers build [AngularJS](http://angularjs.org) based 
applications in type safe manner with [Scala](http://www.scala-lang.org/) language. 

To achieve this goal, it depends on [Scala.js](http://www.scala-js.org/) to provide bindings 
to core AngularJS classes and functions, as well as its own APIs to enable Scala developers 
to access them in more natural manner.

It's still at the very early stage of development, so the most parts of the project are 
subject to frequent and extensive changes.

And the bindings are by no means comprehensive or exhaustive for now, so please use your 
own discretion.

## How to Use

### SBT Settings

Add the following line to your ```sbt``` build definition:

```scala
libraryDependencies += "com.greencatsoft" %%% "scalajs-angular" % "0.1-SNAPSHOT"
```

You'll also need to add _Sonatype Snapshot Repository_ to the resolver as follows, 
because at this moment, there's no release build available yet:

```scala
resolvers += 
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
```

_Important Note! : We don't have a public repository yet, so you'll have to clone it and 
run ```sbt publishLocal``` first to build it yourself._ 

### Defining a Module

You can define an AngularJS module in the following manner. Note that ```config``` and 
```controller``` methods can be chained and take variable arguments.

```scala
val module = angular.module("myproject", Array("ngRoute", "ui.bootstrap"))

Module(module)
	.config(RoutingConfig, HttpTransformerConfig)
	.controller(UserListController, UserDetailController)
	.run(AppInitializer)
```

### Managing Dependencies

Various core AngularJS services like ```HttpService``` or ```Location``` can be injected 
into a controller class by implementing the correspondent traits, like ```HttpServiceAware```,
```LocationAware```, etc.

An example of a simple controller is as follows:

```scala
object ExampleController extends HttpServiceAware with LocationAware {

  override def initialize() {
    val url = location.absUrl + "/example"
    http.get(url).success(success).error(failure)
  }
}
```

As ```ExampleController``` object implements ```HttpServiceAware``` trait, it can access 
```$http``` service provided by AngularJS with the ```http``` member variable defined in the 
trait.

### Using Scopes

Normally, you'll need to retrieve some data from the server with the ```http``` service, and 
make it available to the HTML template by assigning it to a property of the ```$scope```.

To achieve this, the controller can implement either the ```ScopeAware``` or the ```RootScopeAware```
trait.

```scala
object UserDetailsController extends HttpServiceAware with ScopeAware {

  override type DataType = UserForm

  override def initialize() {
    // (read the user information from the server)
    http.get(url).success(...).error(...)

    scope.id = user.id
    scope.name = user.name
    scope.email = user.email

    dynamicScope.delete = () => userService.delete(scope.id)
  }

  class UserForm extends js.Object {

    var id: String

    var name: String

    var email: String
  }
}
```

``ScopeAware`` defines an abstract type member ```DataType``` with which you can access 
the scope object in a type safe manner. Due to a restriction in Scala.js, the target class 
should inherit from the ```js.Object``` and you _cannot_ declare any methods in it. 

To workaround the problem, you need to cast the ```scope``` variable into ```js.Dynamic``` 
first, and accessing it in a dynamic manner. To facilitate the process, ```ScopeAware``` 
provides ```dynamicScope``` variable, which is just a dynamic version of the same ```scope```
variable.

### Defining Routes

Defining routing rules is quite straight forward, like the following example:

```scala
object RoutingConfig extends RouteProviderAware {

  override def initialize() {
    routeProvider
      .when("/", Route("/assets/templates/home.html", Some("Home"), None))
      .when("/signup", Route(SignUpController))
      .when("/users", Route(UserListController))
  }
}
```

Note that you can make your controller implement the ```TemplateController``` trait and 
register it directly to the ```routeProvider``` as shown above.

### Limitations

As mentioned earlier, the project is at the very early stage of development, and misses 
a few bindings to some core classes or functions. Notable example is the support for the 
directives, which is planned in near future.

On the other hand, we possibly won't add support for services as they can be much more 
conveniently done using Scala's companion objects.

### License

This project is provided under the terms of _Apache License, Version 2.0_. 
