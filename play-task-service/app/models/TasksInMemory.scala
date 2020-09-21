package models

object TasksInMemory {
  private var tasks: Map[String, String] = Map.empty

  def addTask(task: String) = {
    val UUID = java.util.UUID.randomUUID.toString
    tasks += (UUID -> task)
  }

  def removeTask(taskId: String) = {
    tasks -= taskId
  }

  def getTasks() = {
    tasks.toList
  }

  def updateTask(taskId: String, task: String) = {
    if(!tasks.contains(taskId)) false
    else {
      tasks += (taskId -> task)
      true
    }
  }
}
