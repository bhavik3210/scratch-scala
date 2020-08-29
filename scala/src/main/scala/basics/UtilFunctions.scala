package basics

object UtilFunctions {

  //explicit return type
  def plusOneOrZero(number: Int): Int = {
    if (number <= 0) 0 else number + 1
  }

  //inferred return type
  def product(a: Int, b: Int): Int = {
    a * b
  }

  def productShort(a: Int, b: Int): Int = a * b

  def productAnnonymous: (Int, Int) => Int = (a: Int, b: Int) => a * b

  def stringToInt(in: String): Either[String, Int] = {
    try {
      Right(in.toInt)
    } catch {
      case e: NumberFormatException => Left(s"Error: ${e.getMessage}")
    }
  }

  def matchNumber(number: Int): String = {
    number match {
      case 0 => "zero"
      case 5 => "five"
      case 9 => "nine"
      case _ => "nothing matched but matched anything/leave it as a last case for matching otherwise it will short circuit"
    }
  }

  def matchBooksFull(book: Book): String = {
    book match {
      case Book(title, yearPublished, author, isbn) => s"$title | $yearPublished | $author | $isbn"
      case _ => "not found"
    }
  }

  def matchBooksPartial(book: Book): String = {
    book match {
      case Book(_, yearPublished, _, _) => s"$yearPublished"
      case _ => "not found"
    }
  }

  def matchNumberList(list: List[Int]): Int = {
    list match {
      case List(a, b, c) => b
      case _ => -1
    }
  }

  def matchNumber2List(list: List[Int]): Int = {
    list match {
      case List(_, b, _) => b
      case _ => -1
    }
  }

  def matchNumber3List(list: List[Int]): Int = {
    list match {
      case List(_, b, _*) => b
      case _ => -1
    }
  }
}

//companion objects
object Math {
  def sum(a: Int, b: Int): Int = a + b

  def getPrivateMember: Int = new Math().max
}

class Math {
  private val max = 100
}

//companion with apply
object Person {
  def apply(firstName: String, lastName: String) = new Person(firstName, lastName)
}

class Person(firstName: String, lastName: String) {
  def getName: String = s"$firstName $lastName"
}

//case classes
case class Fruit(name: String)

case class Course(title: String, author: String)

case class Book(title: String, author: String, yearPublished: String, isbn: String)

case class Trip(to: String)

case class Car(model: String)

case class Cash(model: String)

case class NoPrize()

case class Email(from: String, body: String)
