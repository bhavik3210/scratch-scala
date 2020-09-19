package controllers

import javax.inject.{Inject, Singleton}
import model.TaskListInMemoryModel
import play.api.mvc._
import play.api.i18n._

@Singleton
class TaskList1 @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login = Action { implicit request =>
    Ok(views.html.login1())
  }

  def validateLoginGet(username: String, password: String) = Action {
    Ok(s"GET: $username logged in with $password")
  }

  def validateLoginPost = Action { implicit request =>
    val body = request.body.asFormUrlEncoded
    body.map { args =>
      val username = args("username").head
      val password = args("password").head

      if (TaskListInMemoryModel.validateUser(username, password))
        Redirect(routes.TaskList1.taskList()).withSession("username" -> username)
      else
        Redirect(routes.TaskList1.login()).flashing("error" -> "Invalid username/password combo")

    }.getOrElse(Redirect(routes.TaskList1.login()))
  }

  def createUser = Action { implicit request =>
    val body = request.body.asFormUrlEncoded
    body.map { args =>
      val username = args("username").head
      val password = args("password").head

      if (TaskListInMemoryModel.createUser(username, password))
        Redirect(routes.TaskList1.taskList()).withSession("username" -> username)
      else
        Redirect(routes.TaskList1.login()).flashing("error" -> "failed to create a user")

    }.getOrElse(Redirect(routes.TaskList1.login()))
  }

  def taskList = Action { implicit request =>
    val usernameOption = request.session.get("username")
    usernameOption.map { username =>
      val tasks = TaskListInMemoryModel.getTasks(username)
      Ok(views.html.taskList1(tasks))
    }.getOrElse(Redirect(routes.TaskList1.login()))
  }

  def logout = Action {
    Redirect(routes.TaskList1.login()) withNewSession
  }
}
