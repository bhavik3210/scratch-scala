package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{BaseController, ControllerComponents}

@Singleton
class TaskController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{

  def addTask() = TODO

  def removeTask(task: String) = TODO

  def getTasks(taskId: String) = TODO

  def updateTask(taskId: String) = TODO
}
