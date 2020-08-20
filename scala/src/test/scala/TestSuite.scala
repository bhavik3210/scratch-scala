import UtilFunctions._
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

class TestSuite extends AnyFunSuite {

  test("test immutable vs mutable") {
    var mutableVariable = "mutable"
    mutableVariable = "immutable"

    val immutableVariable = "immutable"

    assert(mutableVariable == immutableVariable, true)
  }

  test("test conditionals") {
    val weekdayThatStartsWithLetterM = Array("Monday")

    // NO Explicit returns
    val day = if (!weekdayThatStartsWithLetterM.isEmpty) {
      weekdayThatStartsWithLetterM(0)
    } else {
      "No weekdays that starts with Letter M"
    }

    assert(day == "Monday", true)
    assert(day != "Sunday", false)
  }

  test("test imperative loops") {
    val letters = List("a", "b", "d", "e", "f")
    val numbers = List(1, 2, 3, 4)

    // Simple iteration
    val mutableResultList = new ListBuffer[String]()
    for (letter <- letters) {
      // letter = "asdf" not allowed /immutable by default
      mutableResultList += letter
    }
    assert(mutableResultList.size == letters.size, true)

    // Condition filter
    val mutableEvenNumbers = new ListBuffer[Int]()
    for (
      number <- numbers
      if (number % 2 == 0)
    ) {
      mutableEvenNumbers += number
    }
    assert(mutableEvenNumbers.size == 2, true)

    // Double conditions
    val mutableEvenNumbersGreaterThanTwo = ListBuffer[Int]()
    for (
      number <- numbers
      if (number % 2 == 0)
      if (number > 2)
    ) {
      mutableEvenNumbersGreaterThanTwo += number
    }
    assert(mutableEvenNumbersGreaterThanTwo.size == 1, true)

    // Nested for loops
    val numbersAndLettersCombo = ListBuffer[String]()
    for {
      number <- numbers
      letter <- letters
    } numbersAndLettersCombo += s"$number => $letter"
    assert(numbersAndLettersCombo.size == numbers.size * letters.size, true)
  }

  test("test functional loops (yield)") {
    val letters = List("a", "b", "d", "e", "f")
    val numbers = List(1, 2, 3, 4)

    //returns a new immuatable copy
    val numbers3x = for (number <- numbers) yield {
      number * 3
    } //braces are optionals for single
    assert(numbers3x.size == numbers.size, true)
    assert(numbers3x.head == 3, true)
    assert(numbers3x(1) == 6, true)
    assert(numbers3x(2) == 9, true)
    assert(numbers3x(3) == 12, true)
    assert(numbers3x.head != 1, true)
    assert(numbers3x(1) != 2, true)
    assert(numbers3x(2) != 3, true)
    assert(numbers3x(3) != 4, true)

    val evenNumbers3x = for (number <- numbers if (number % 2 == 0)) yield number * 3
    assert(evenNumbers3x.size != numbers.size, true)
    assert(evenNumbers3x.head == 6, true)
    assert(evenNumbers3x(1) == 12, true)
    assert(evenNumbers3x.head != 2, false)
    assert(evenNumbers3x(1) != 4, false)

    val numbersAndLetters = for {
      number <- numbers
      letter <- letters
    } yield s"$number => $letter"
    assert(numbersAndLetters.size == numbers.size * letters.size, true)
  }

  test("test basic function") {
    val negativeNumber = plusOneOrZero(-1)
    val zeroNumber = plusOneOrZero(0)
    val positiveNumber = plusOneOrZero(4)

    assert(negativeNumber == 0, true)
    assert(negativeNumber != -1, true)
    assert(zeroNumber == 0, true)
    assert(positiveNumber == 5, true)
    assert(positiveNumber != 4, true)

    val zeroNumberProduct = product(0, 4)
    val positiveNumberProduct = productShort(4, 2)
    val productAnonymous = productAnnonymous(4, 2)

    assert(zeroNumberProduct == 0, true)
    assert(zeroNumberProduct != 4, true)

    assert(positiveNumberProduct != 4, true)
    assert(positiveNumberProduct != 2, true)
    assert(positiveNumberProduct == 8, true)

    assert(productAnonymous != 4, true)
    assert(productAnonymous != 2, true)
    assert(productAnonymous == 8, true)
  }

  test("anonymous function") {
    val apple = Fruit("apple")
    val orange = Fruit("orange")
    val banana = Fruit("banana")
    val fruitBasket = List(apple, orange, banana, apple, orange, banana, banana, orange)

    val filteredList = fruitBasket.filter(fruit => fruit.name == "apple")

    assert(filteredList.size == 2, true)
    assert(filteredList.size != fruitBasket.size, false)

    val similarFilteredList = fruitBasket.filter(_.name == "apple")

    assert(similarFilteredList.size == 2, true)
    assert(similarFilteredList.size != fruitBasket.size, false)
  }

  test("companion class and object") {
    val result = Math.sum(10, 10)
    val resultMax = Math.getPrivateMember

    assert(result == 20, true)
    assert(resultMax == 100, true)

    val person = Person("First", "Last")
    val name = person.getName
    assert(name == "First Last", true)
  }

  /**
   * - companion objects and apply comes with it
   * - immutable arguments in parameter list
   * - copy method to make modified copies
   * - other functionality by default
   * - pattern matching
   */
  test("case classes") {
    val scalaCourseA = Course("Scala Course", "Some Guy")
    val scalaCourseB = Course("Scala Course", "Some Guy")
    val kotlinCourse = Course("Kotlin Course", "Some Guy")

    assert(scalaCourseA == scalaCourseB, true)
    assert(scalaCourseA != kotlinCourse, false)
    assert(scalaCourseA.title == "Scala Course", true)
    assert(scalaCourseA.author == "Some Guy", true)
    assert(kotlinCourse == kotlinCourse.copy(), true)
  }

  test("options") {
    val employees = Set("John", "Sam", "Mary", "Stacie")
    val someResult = employees.find(_ == "John")
    val noneResult = employees.find(_ == "Frank") //noneResult is None

    assert(someResult.isInstanceOf[Option[String]], true)
    assert(someResult.isInstanceOf[Some[String]], true)
    assert(noneResult.isInstanceOf[Option[String]], true)
    assert(noneResult.isEmpty, true)

    assert(someResult.isDefined, true)
    assert(!noneResult.isDefined, false)

    //getOrElse
    val john = employees.find(_ == "John").getOrElse("Employee not found")
    val frank = employees.find(_ == "Frank").getOrElse("Employee not found")

    assert(john == "John", true)
    assert(john != "Employee not found", false)
    assert(frank == "Employee not found", true)
    assert(frank != "Frank", true)
  }

  test("errors and exceptions") {
    val outcome = Try(10 / 0)
    assert(!outcome.isSuccess, false)
    assert(outcome.isFailure, true)

    assert(outcome.isInstanceOf[Failure[String]], true)
    assert(!outcome.isInstanceOf[Success[String]], false)
  }

  test("Either") {
    val numberReturned = stringToInt("5")
    val notANumberReturned = stringToInt("e")

    assert(numberReturned == Right(5), true)
    assert(notANumberReturned == Left("Error: For input string: \"e\""), true)
  }

  test("pattern match") {
    val number = 5
    val number2 = 2

    val number5InWord = matchNumber(number)
    val number2InWord = matchNumber(number2)

    assert(number5InWord == "five", true)
    assert(number2InWord == "nothing matched but matched anything/leave it as a last case for matching otherwise it will short circuit", true)
  }

  test("pattern match 2") {
    val programmingInScala = Book("Programming in Scala", "2016", "Martin", "0998092323")
    val programmingNotInScala = Book("Programming not in Scala", "2017", "Martin", "0998092323")
    val programmingNoMore = Book("Programming no more", "2050", "Martin", "0998092323")

    val result1 = matchBooksFull(programmingInScala)
    val result2 = matchBooksPartial(programmingNoMore)

    assert(result1 == "Programming in Scala | 2016 | Martin | 0998092323")
    assert(result2 == "2050", true)
  }

  test("pattern match 3") {

  }

}
