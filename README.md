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

And the bindings are by no means comprehensive or exhaustive for now, so please use it at 
your discretion.

## How to Use

### SBT Settings

Add the following line to your ```sbt``` build definition:

```scala
libraryDependencies += "com.greencatsoft" %%% "scalajs-angular" % "0.2"
```

If you want to test the latest snapshot version instead, change the version to 
_"0.3-SNAPSHOT"_ and add _Sonatype Snapshot Repository_ to the resolver as follows: 

```scala
resolvers += 
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
```

### Defining a Module

You can define an AngularJS module in the following manner. Note that ```config``` and 
```controller``` methods can be chained and take variable arguments.

```scala
val module = Angular.module("myproject", Seq("ngRoute", "ui.bootstrap"))

module.config(RoutingConfig)
module.factory(UserServiceFactory)
module.controller(UserDetailController)
module.directive(UserInfoDirective)
module.run(AppInitializer)
```

_Note: Although the API itself supports method chaining, it might not work correctly in this 
version due to a limitation in current macro implementation._

### Managing Dependencies

You can find core AngularJS services like ```HttpService``` or ```Location``` in the 
```core``` package, while those from any third party modules reside in the ```extensions```
package.

And such dependencies can be injected into any object which inherits from the ```Service``` 
trait, including ```Controller```, ```Directive```, ```Factory```, and more.

To inject a specific dependency, you can declare a variable with the ```@inject``` 
annotation like the following example:

```scala
object ExampleController extends Controller {

  @inject
  var location: Location = _

  @inject
  var http: HttpService = _

  // You can assume all dependencies to be resolved 
  // after this method is invoked.
  override def initialize() {
    super.initialize()

    val url = location.absUrl + "/example"
    http.get(url).success(success).error(failure)
  }
}
```

You can also declare your own service, by annotating it with the ```@injectable``` 
annotation, and register it using a factory:

```scala
@injectable("$taskService")
class TaskService(val http: HttpService) {
  // implement service methods.
}

object TaskServiceFactory extends Factory[TaskService] {

  override val name = "$taskService"

  @inject
  var http: HttpService = _

  override def apply(): TaskService = new TaskService(http)
}

object TaskController extends Controller {

  @inject
  var service: TaskService = _

  override def initialize() {
    // Use the injected service.
  }
}
```

### Using Controllers

For the sake of example, let's assume that you have a REST API url ```"/users/john"``` that produces a JSON output that could be unpickled to the following ```User``` case class :

```scala
@JSExportAll
case class User(id : String, name : String, email : String, friends : Array[String])
```

To see an example of how such unpickling can be done, see [the TaskService from the TodoMVC sample](https://github.com/greencatsoft/scalajs-angular-todomvc/blob/master/scalajs/src/main/scala/todomvc/example/TaskService.scala)

Normally, you'll need to retrieve some data from the server with the ```http``` service, and 
make it available to the HTML template by assigning it to a property of the ```$scope```.

```scala
object UserDetailsController extends Controller with HttpServiceAware {

  // If not overriden, it'll use the simple class name.
  override val name = "UserDetailsCtrl"

  override type ScopeType = UserForm

  @inject
  var http: HttpService = _

  override def initialize(scope: ScopeType) {
    val future: Future[User] = http.get("/users/john")

    future onComplete {
      case Success(user) => {
        scope.id = user.id
        scope.name = user.name
        scope.email = user.email
        scope.friends = user.friends
      }
      case Failure(t) => println("An error has occured: " + t.getMessage)
    }

    scope.dynamic.delete = () => userService.delete(scope.id)
  }

  trait UserForm extends Scope {

    var id: String
    var name: String
    var email: String

    var friends: js.Array[String]
  }
}
```

``ScopeAware`` (which is inherited from the ```Controller``` trait) defines an abstract 
type member ```ScopeType``` with which you can access the scope object in a type safe 
manner. Due to a restriction in Scala.js, the target class should inherit from the 
```js.Object``` and you _cannot_ declare any methods in it. 

To workaround the problem, you need to cast the ```scope``` variable into ```js.Dynamic``` 
first, and accessing it in a dynamic manner. To facilitate the process, ```ScopeAware``` 
provides ```scope.dynamic``` method, which returns a dynamic version of the same ```scope```
variable.

Alternatively, you can rewrite the above example in a more compact form as follows :

```scala
@JSExport
object UserDetailsController extends Controller {

  @inject
  var http: HttpService = _

  override def initialize(scope : ScopeType) {
    val future: Future[User] = http.get("/users/john")

    future onComplete {
      case Success(user) => {
        scope.id = user.id
        scope.name = user.name
        scope.email = user.email
        scope.friends = user.friends
      }
      case Failure(t) => println("An error has occured: " + t.getMessage)
    }
  }

  @JSExport
  def delete() {
    userService.delete(scope.id)
  }

  trait ScopeType extends Scope {

    var id: String
    var name: String
    var email: String
    var friends: js.Array[String]
  }
}
```
In this case, you can refer to the _delete_ method from your template as _controller.delete()_.

### Using Directives

To define a directive, you can declare an object which implements ```Directive``` trait.

You can also mixin such traits as ```ElementDirective```, ```AttributeDirective```, 
```Requires```, and so on to assign more specific behaviors to your directive implementation.

Scope related configuration can also be specified by mixing in one of ```InheritParentScope```, 
```UseParentScope```, or ```IsolatedScope``` traits.

```IsolatedScope``` also provides its own DSL to specify attribute bindings, as specified by 
_AngularJS_ API:

```scala
object CustomerDirective extends ElementDirective 
  with TemplatedDirective with IsolatedScope {
 
  override val name = "myCustomer"

  override val templateUrl = "my-customer-iso.html"

  bindings ++= Seq(
    "customerInfo" := "info",
    "title" :@ "",
    "close" :& "onClose"
  )
}
```

To implement a directive which manipulates DOM elements, you can override the ```link```
method as follows:

```scala
object LocationDirective extends AttributeDirective {

  override val name = "currentLocation"

  @inject
  var location: Location = _

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
    val elem = elems.head.asInstanceOf[HTMLElement]

    elem.innerHTML = location.path
  }
}
```

### Defining Routes

Defining routing rules is quite straight forward, like the following example:

```scala
object RoutingConfig extends Config {

  @inject
  var routeProvider: RouteProvider = _

  override def initialize() {
    routeProvider
      .when("/", Route("/assets/templates/home.html", "Home"))
      .when("/signup", Route(SignUpController))
      .when("/users", Route(UserListController))
  }
}
```

_(Note that you'll need to 'ngRoute' in your dependency list, and angular-route.js in the 
html file for the above code to work)_

You can make your controller implement the ```PageController``` trait and 
register it directly to the ```routeProvider``` as shown above.

### Example Project

There's an example implementation of [TodoMvc](http://www.todomvc.com) application as a separate project:

* https://github.com/greencatsoft/scalajs-angular-todomvc

### License

This project is provided under the terms of _Apache License, Version 2.0_. 
