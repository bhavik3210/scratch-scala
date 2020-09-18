package controllers

import javax.inject.{Inject, Singleton}
import model.TaskListInMemoryModel
import play.api.mvc._
import play.api.i18n._

@Singleton
class TaskList1 @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login = Action {
    Ok(views.html.login1())
  }

  def validateLoginGet(username: String, password: String) = Action {
    Ok(s"GET: $username logged in with $password")
  }

  def validateLoginPost = Action { request =>
    val body = request.body.asFormUrlEncoded
    body.map { args =>
      val username = args("username").head
      val password = args("password").head

      if (TaskListInMemoryModel.validateUser(username, password))
        Redirect(routes.TaskList1.taskList())
      else
        Redirect(routes.TaskList1.login())

    }.getOrElse(Redirect(routes.TaskList1.login()))
  }

  def taskList = Action {
    val tasks = List("task1", "task2", "task3")
    Ok(views.html.taskList1(tasks))
  }

}
