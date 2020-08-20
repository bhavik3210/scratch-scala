object UtilFunctions {

  //explicit return type
  def plusOneOrZero(number: Int): Int = {
    if (number <= 0) 0 else number + 1
  }

  //inferred return type
  def product(a: Int, b: Int) = {
    a * b
  }

  def productShort(a: Int, b: Int) = a * b

  def productAnnonymous = (a: Int, b: Int) => a * b

  def stringToInt(in: String): Either[String, Int] = {
    try {
      Right(in.toInt)
    } catch {
      case e: NumberFormatException => Left(s"Error: ${e.getMessage}")
    }
  }
}

//companion objects
object Math {
  def sum(a: Int, b: Int): Int = a+b
  def getPrivateMember = new Math().max
}
class Math {
  private val max = 100
}

//companion with apply
object Person {
  def apply(firstName: String, lastName: String) = new Person(firstName, lastName)
}
class Person (firstName: String, lastName: String) {
  def getName: String = s"$firstName $lastName"
}

//case classes
case class Fruit(name: String)

case class Course(title: String, author: String)