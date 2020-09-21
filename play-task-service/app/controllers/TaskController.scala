package controllers

import javax.inject.{Inject, Singleton}
import models.TasksInMemory
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}

case class Task(task: String)

@Singleton
class TaskController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def addTask() = Action { implicit request =>
    val body = request.body.asJson
    body.map { args =>
      val task = args("task")
      println(task)
      if (!task.toString().isEmpty) {
        TasksInMemory.addTask(task.toString())
        Ok(Json.toJson(TasksInMemory.getTasks()))
      } else Created
//      Ok
    }.getOrElse(NotFound)
  }

  def removeTask(taskId: String) = TODO

  def getTasks() = Action { implicit request =>
    Ok(Json.toJson(TasksInMemory.getTasks()))
  }

  def updateTask(taskId: String) = TODO
}
