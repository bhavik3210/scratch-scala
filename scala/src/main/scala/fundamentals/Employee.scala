package fundamentals

object Employee {
  private val departments: Map[String, String] = Map(
    "HR" -> "Human Resources",
    "ACT" -> "Accounting",
    "R&D" -> "Research and Development",
    "MKT" -> "Marketing",
    "SLS" -> "Sales",
    "OPS" -> "Operations"
  )

  def apply(firstName: String, lastName: String, stockOptions: Int): Employee =
    new Employee(firstName, lastName, stockOptions)

  def getDepartmentNameByCode(code: String): String =
    departments.getOrElse(code, "Doesn't exist.")
}

class Employee(firstName: String, lastName: String, stockOptions: Int) {
  private val first: String = firstName
  private val last: String = lastName
  private val stocks: Int = stockOptions

  def getFirst: String = first

  def getLast: String = last

  def getStocks: Int = stocks

  // def addMoreStocks(numberOfStocks: Int) = stocks += numberOfStocks (avoid immutability by doing below)
  def addMoreStocks(numberOfStocks: Int): Employee = Employee(first, last, stocks + numberOfStocks) //aka functional objects

  override def toString: String = s"$first $last $stocks"
}
