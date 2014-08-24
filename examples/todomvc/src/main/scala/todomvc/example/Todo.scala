package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.Any.{ fromBoolean, fromString }
import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.annotation.JSExport

@JSExport
case class Todo(_title: String, _completed: Boolean = false) {
  require(_title != null, "Missing argument '_title'.")

  @JSExport
  var title = _title

  @JSExport
  var completed = _completed

  override def clone = Todo(title, completed)

  def toJSON: js.Object = literal("title" -> title, "completed" -> completed)
}

object Todo {

  def apply(json: js.Object): Todo = {
    val obj = json.asInstanceOf[js.Dynamic]

    val title = obj.title.asInstanceOf[String]
    val completed = obj.completed.asInstanceOf[Boolean]

    Todo(title, completed)
  }
}