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

Add the following lines to your ```sbt``` build definition:

```scala
libraryDependencies += "com.greencatsoft" %%% "scalajs-angular" % "0.7"
```

If you want to test the latest snapshot version instead, change the version to 
```0.8-SNAPSHOT``` and add Sonatype snapshot repository to the resolver as follows: 

```scala
resolvers += 
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
```

Or more simply as,
```scala
resolvers += Resolver.sonatypeRepo("snapshots")
```

### Defining a Module

You can define an AngularJS module in the following manner:

```scala
val module = Angular.module("myproject", Seq("ngRoute", "ui.bootstrap"))
```

And you can either register your Angular components like a controller either as a class, 
or as a singleton object like below: 

```scala
// In case of registering a class
module
  .config[RoutingConfig]
  .factory[UserServiceFactory]
  .controller[UserDetailController]
  .directive[UserInfoDirective]
  .run[AppInitializer]
  .filter[UpperCaseFilter]

// In case of registering a singleton object
module
  .config(RoutingConfig)
  .factory(UserServiceFactory)
  .controller(UserDetailController)
  .directive(UserInfoDirective)
  .run(AppInitializer)
  .filter(UpperCaseFilter)
```

Any classes or objects which is to be registered as an Angular component should be inheriting 
from ```Service``` trait (or one of its subtypes, like ```Controller```). And in case with 
injectable services, like controllers, factories, directives, or filters, you need to specify 
its name using the ```@injectable``` annotation like shown below:

```scala
@injectable("todoEscape")
class EscapeDirective extends AttributeDirective {
  ...
}
```

In case of a factory, both the factory itself and its product type should be annotated with 
```@injectable``` using the same name:

```scala
@injectable("taskService")
class TaskService(http: HttpService) extends Service {
  ...
}

@injectable("taskService")
class TaskServiceFactory(http: HttpService) extends Factory[TaskService] {
  ...
}
```
### Managing Dependencies

You can find core AngularJS services like ```HttpService``` or ```Location``` in the 
```core``` package, while those from any third party modules reside in the ```extensions```
package.

And such dependencies can be injected into any object which inherits from the ```Service``` 
trait, including ```Controller```, ```Directive```, ```Factory```, and more.

From ```0.5``` version onward, it also supports constructor based dependency injection (which 
was first attempted by an alternate implementation of the Angular.js API in Scala.js, 
[scalajs-angulate](https://github.com/jokade/scalajs-angulate)) as well as the traditional 
property based approach.

In general, the constructor based approach should be preferred, as it adheres to the Scala's 
principle with using immutable properties. However, if you want to declare your Angular module 
as a singleton object, you need to use the property based method instead, as a singleton does 
not have a constructor.

Note that you can mix both types of the injection method in a single component, which might be 
useful if you're declaring each dependencies as separate traits, like ```LocationAware```.

#### Constructor Based Dependency Injection

You can declare any dependent objects as constructor arguments of the target class you want 
them to be injected into:

```scala
@injectable("todoCtrl")
class TodoCtrl(scope: TodoScope, location: Location, service: TaskService)
  extends AbstractController[TodoScope](scope) {

  scope.todoItems = service.getItems()
  ...
}
```

Using this method, you can inject any Angular components (including your own services) into 
any other components, provided that they have the ```@injectable``` annotation.

As the class instantiation is handled by Angular itself, you can only include valid Angular 
components in your constructor argument list.

Note that the ```@injectable``` annotation need not be declared on the immediate type of an 
argument. For example, ```TodoScope``` in the above example inhertis from ```Scope``` trait 
which is annotated with ```@injectable("$scope")```, so you don't have to declare another 
annotation on the ```TodoScope``` trait itself. 

#### Property Based Dependency Injection

In case of using a Scala object as Angular component, you need to inject any dependencies 
as properties (variables) instead of constructor arguments.

To inject a specific dependency, you can declare a variable with the ```@inject``` 
annotation like the following example:

```scala
@injectable("exampleCtrl")
object ExampleController extends Controller[Scope] {

  @inject
  var location: Location = _

  @inject
  override var scope: Scope = _

  // You can assume all dependencies to be resolved 
  // after this method is invoked.
  override def initialize() {
    super.initialize()

    val url = location.absUrl + "/example"
  }
}
```

All injected types must have a valid ```@injectable``` annotation in one of the types in 
their class hierarchy as mentioned previously.

One of the notable differences from the constructor based method is that, you cannot access 
injected objects in the constructor block of the object because they are not available at 
the time of the object creation.

In order to solve this problem, the ```Service``` trait extends from ```Initializable``` 
which provides a method ```def initialized(): Unit``` which is invoked after all dependencies 
are injected to the service (like ```@PostConstruct``` in Java). 

### Using Controllers and Scopes

Controller is a special type of Angular service, which is used to communicate with a view 
template by manipulating a scope object. As such, they are usually tightly coupled with 
associated scope objects, so their relationship is reflected in the signature of the trait 
which represents Angular controllers, as ```Controller[A <: Scope]```.

Normally, you would define a scope trait and declare any properties or functions you want to 
access from your controller class, then write a matching controller class or an object using 
the name of the scope trait as the type parameter of the ```Controller``` trait (or 
the ```AbstractController``` class for convenience).

A typical scope would look like an example below:

```scala
trait UserScope extends Scope {

  var id: String = js.native
  var name: String = js.native
  var email: String = js.native
  var friends: js.Array[String] = js.native

  var delete: js.Function = js.native
}
```

Note that you cannot specify a default value for a property, or write an implementation 
of a function of your scope trait, since ```Scope``` inherits from ```js.Object``` and Scala.js 
does not support such an use case yet.

So, typically, they are initialized from a constructor block, or inside the ```initialize```
method in case of an object, as shown below:

```scala
@injectable("userDetailsCtrl")
class UserDetailsController(scope: UserScope, http: HttpService) 
  extends AbstractController[UserScope](scope) {

  val future: Future[User] = http.get("/users/john")

  future onComplete {
    case Success(user) => {
      scope.id = user.id
      scope.name = user.name
      scope.email = user.email
      scope.friends = user.friends
    }
    case Failure(t) => 
      println("An error has occured: " + t.getMessage)
  }

  scope.delete = () => userService.delete(scope.id)
}
```
By default, the controller instance is automatically exported to the associated scope 
as ```controller``` variable. So, you can access an arbitrary method after you put 
```@JSExport``` annotation on the method and on the controller class which encloses it: 

```scala
@JSExport
@injectable("userDetailsCtrl")
class UserDetailsController(scope: UserScope, http: HttpService) 
  extends AbstractController[UserScope](scope) {
  ...
  @JSExport
  def delete(): Unit = userService.delete(scope.id)
}
```
```html
<div ng-controller="userDetailsCtrl">
  ...
  <button ng-click="controller.delete()">Delete</button>
</div>
```

The same rule applies to the case when you use the ```controller-as``` syntax, because 
you cannot directly refer to the controller instance due to a limitation in the 
implementation.

So, if you have declared your controller as _TodoCtrl as todo_ for instance, you can 
invoke its _checkAll()_ method with _todo.controller.checkAll()_(instead of _todo.checkAll()_).

As a final note, ```Controller``` (and ```Directive```) provides implicit conversion
from ```Scope``` to ```js.Dynamic``` via ```scope.dynamic``` method, so you can use 
this feature to attach arbitrary properties or functions to the scope object without 
declaring them first:

```scala
@injectable("userDetailsCtrl")
class UserDetailsController(scope: Scope, http: HttpService) 
  extends AbstractController[Scope](scope) {
  ...
  scope.dynamic.delete = () => userService.delete(scope.id)
}
```

### Using Services and Factories

It is recommended to implement service facades (or business delegates) are as plain 
Scala objects without depending any Angular specific APIs, for the sake of cleaner 
separation between layers.

But if you need to inject Angular components to your service object, you might want them 
to be registered as an Angular service as well, using ```Module.service``` method as 
shown below:


```scala
@injectable("taskService")
object TaskService extends Service {

  var http: HttpService = _

  override def initialize() {
    ...
  }
}
...
module.service(TaskService)
```

Better still, you can rewrite the above example using ```Factory[A]``` instead:

```scala
@injectable("taskService")
class TaskService(http: HttpService) {
  ...
}

@injectable("taskService")
class TaskServiceFactory(http: HttpService) extends Factory[TaskService] {
  override def apply() = new TaskService(http)
}
...
module.factory[TaskServiceFactory]
```

### Using Directives

To define a directive, you can declare an object which implements ```Directive``` trait.

You can also mixin such traits as ```ElementDirective```, ```AttributeDirective```, 
```Requires```, and so on to assign more specific behaviors to your directive implementation.

Scope related configuration can also be specified by mixing in one of ```InheritParentScope```, 
```UseParentScope```, or ```IsolatedScope``` traits.

```IsolatedScope``` also provides its own DSL to specify attribute bindings, as specified by 
_AngularJS_ API:

```scala
@injectable("myCustomer")
class CustomerDirective extends ElementDirective 
  with TemplatedDirective with IsolatedScope {
 
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
@injectable("currentLocation")
class LocationDirective(location: Location) extends AttributeDirective {

  override def link(
    scope: ScopeType, elems: Seq[Element], attrs: Attributes, controllers: Controller[_]) {
    val elem = elems.head.asInstanceOf[HTMLElement]

    elem.innerHTML = location.path
  }
}
```

### Using Filters

To define a filter, you can declare an object which implements ```Filter[A]``` trait, and 
override the ```filter``` method to handle the actual filtering:

```scala
@injectable("upper")
class UpperCaseFilter extends Filter[String] {

  override def filter(item: String): String = item.toUpperCase
}
```

As with other types inheriting from the ```Service``` trait, you can inject dependencies 
to your filter instance using the ```@inject``` annotation, and it also provides an alternative 
```filter``` method which takes additional arguments:

```scala
@injectable("upper")
class UpperCaseFilter(location: Location) extends Filter[String] {

  override def filter(item: String, args: Seq[Any]) = 
    if (location.path.endsWith(args.head.toString)) 
      item.toUpperCase
    else
      item.toLowerCase
}
```

### Defining Routes

Defining routing rules is quite straight forward, like the following example:

```scala
class RoutingConfig(routeProvider: RouteProvider) extends Config {

  routeProvider
    .when("/", Route("/assets/templates/home.html", "Home"))
    .when("/signup", Route("/assets/templates/signup.html", "Sign up", "signupCtrl"))
    .when("/users", Route("/assets/templates/users.html", "Users", "usersCtrl"))
}
```

_(Note that you need to 'ngRoute' in your dependency list, and angular-route.js in the 
html file for the above code to work)_

### Asynchronous Task

In order to process asynchronously calculated values in Scala, you need an implicit instance
of _ExecutionContext_ is needed in scope.

Scala.js provides its own implementation as _RunNowExecutionContext_, which simply resolves
the submitted tasks immediately.

However, it's been deprecated since `0.6.6` and replaced by _QueueExecutionContext_ which
is truly asynchronous in nature.

It could pose a problem in the context of an Angular.js application, since it has its own
lifecycle to manage the scope. For example, you might notice that changed values in a scope
are not immediately visible if they are assigend asynchronously, like within `onComplete`
clause of a _Future_ instance.

To deal with this limitation, we provide our own version of _ExecutionContext_, which
is based on Angular.js's ``$timer` service.

The simplest way to use it is to make your controller or service extend from
_AngularExecutionContextProvider_, like shown below:

```scala
// Inject '$timeout' service and extend 'AngularExecutionContextProvider'.
@injectable("todoCtrl")
class TodoCtrl(scope: TodoScope, service: TaskServiceProxy,
  val timeout: Timeout) extends AbstractController[TodoScope](scope)
  with AngularExecutionContextProvider {

  service.findAll() onComplete {
    case Success(tasks) =>
      // This expression is executed within Angular's digest cycle.
      scope.todos = tasks.toJSArray

      update()
    case Failure(t) => handleError(t)
  }
}
```

### Example Project

There's an example implementation of [TodoMvc](http://www.todomvc.com) application as a 
separate project:

* https://github.com/greencatsoft/scalajs-angular-todomvc

### License

This project is provided under the terms of _Apache License, Version 2.0_. 
