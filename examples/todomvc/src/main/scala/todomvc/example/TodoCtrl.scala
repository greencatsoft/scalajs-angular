package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.Any.{ fromBoolean, fromFunction1, fromString, jsArrayOps }
import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.annotation.JSExport
import com.greencatsoft.angularjs.{ NamedTarget, angular }
import com.greencatsoft.angularjs.location.{ Location, LocationAware }
import com.greencatsoft.angularjs.scope.ScopeAware
import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.AbstractController
import scala.scalajs.js.JSON
import com.greencatsoft.angularjs.scope.Scope

/**
 * The main controller for the app.
 *
 * The controller
 * <ul>
 *   <li>retrieves and persiststhe model via the [[services.TodoStorage]] service</li>
 *   <li>exposes the model to the template and provides event handlers</li>
 * </ul>
 *
 * ''Note: instead of providing the required dependencies as arguments to a constructor function,
 * we mix in traits like `LocationAware` to request these dependencies''
 */
@JSExport
object TodoCtrl extends AbstractController("TodoCtrl") with LocationAware {

  val StorageId = "todomvc-example"

  override type ScopeType = TodoScope

  override def initialize() {
    scope.todos = load()
    scope.statusFilter = literal()

    scope.newTitle = ""

    // this reference to the AngularJS $location service is provided by the mixin LocationAware
    scope.location = location

    if (location.path() == "") location.path("/")

    scope.$watch("location.path()", (path: String) =>
      scope.statusFilter = path match {
        case "/active" => literal(completed = false)
        case "/completed" => literal(completed = true)
        case _ => literal()
      })

    scope.$watch("controller.todoData", save _)

    update()
  }

  @JSExport
  def todoData: String = JSON.stringify(scope.todos.map((t: Todo) => t.toJSON))

  def load(): Array[Todo] = {
    type JsonArray = js.Array[js.Object]

    def asSeq(json: String) = JSON.parse(json).asInstanceOf[JsonArray].toSeq

    Option(localStorage.getItem(StorageId)) match {
      case Some(data) => asSeq(data.toString).map(Todo(_)).toArray
      case None => Array.empty
    }
  }

  def save(data: String) {
    localStorage.setItem(StorageId, data)
    update()
  }

  @JSExport
  def update() {
    scope.remainingCount = scope.todos.count(!_.completed)
    scope.allChecked = scope.remainingCount == 0
  }

  @JSExport
  def add() {
    val title = scope.newTitle.trim

    if (title != "") {
      scope.todos +:= Todo(title)
      scope.newTitle = ""
    }
  }

  @JSExport
  def edit(todo: Todo) {
    scope.originalTodo = todo.clone
    scope.editedTodo = todo
  }

  @JSExport
  def doneEditing(todo: Todo) {
    val title = todo.title.trim

    if (title == "") remove(todo)
    else todo.title = title

    scope.editedTodo = null
    scope.originalTodo = null
  }

  @JSExport
  def revertEditing(todo: Todo) {
    scope.todos(scope.todos.indexOf(todo)) = scope.originalTodo

    scope.editedTodo = null
    scope.originalTodo = null
  }

  @JSExport
  def remove(todo: Todo) {
    scope.todos.splice(scope.todos.indexOf(todo), 1)
  }

  @JSExport
  def clearCompleted() {
    scope.todos = scope.todos.filter((t: Todo) => !t.completed)
  }

  @JSExport
  def markAll(completed: Boolean) {
    scope.todos.foreach(_.completed = !completed)
  }
}
