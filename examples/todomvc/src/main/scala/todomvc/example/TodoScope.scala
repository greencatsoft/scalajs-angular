package todomvc.example

import scala.scalajs.js

import com.greencatsoft.angularjs.location.Location
import com.greencatsoft.angularjs.scope.Scope

trait TodoScope extends Scope {

  var todos: js.Array[Todo]

  var newTitle: String

  var editedTodo: Todo

  var originalTodo: Todo

  var allChecked: Boolean

  var remainingCount: Int

  var location: Location

  var statusFilter: js.Dynamic
}