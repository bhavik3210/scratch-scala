package model

object TaskListInMemoryModel {
  private val users = Map[String, String](("mark" -> "pass"))

  def validateUser(username: String, password: String): Boolean = {
    users.get(username).map(_ == password).getOrElse(false)
  }

  def createUser(username: String, password: String): Boolean = ???
  def getTasks(username: String): Seq[String] = ???
  def addTask(username: String, task: String): Unit = ???
  def removeTask(username: String, index: Int): Boolean = ???
}
