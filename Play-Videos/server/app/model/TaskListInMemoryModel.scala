package model

object TaskListInMemoryModel {
  private var users = Map[String, String](("bhavik" -> "password"))
  private val tasks = Map[String, List[String]]("bhavik" -> List("buy groceries", "build workout plan", "do laundry", "work on app"))

  def validateUser(username: String, password: String): Boolean = {
    users.get(username).map(_ == password).getOrElse(false)
  }

  def createUser(username: String, password: String): Boolean = {
    if (users.contains(username)) false
    else users += (username -> password); true
  }

  def getTasks(username: String): Seq[String] = {
    tasks.get(username).getOrElse(Nil)
  }
  def addTask(username: String, task: String): Unit = ???
  def removeTask(username: String, index: Int): Boolean = ???
}
