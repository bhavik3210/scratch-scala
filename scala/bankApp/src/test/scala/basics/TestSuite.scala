package basics

import basics.UtilFunctions._
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.SortedMap
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
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
      if number % 2 == 0
    ) {
      mutableEvenNumbers += number
    }
    assert(mutableEvenNumbers.size == 2, true)

    // Double conditions
    val mutableEvenNumbersGreaterThanTwo = ListBuffer[Int]()
    for (
      number <- numbers
      if number % 2 == 0
      if number > 2
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

    val evenNumbers3x = for (number <- numbers if number % 2 == 0) yield number * 3
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
    val result = basics.Math.sum(10, 10)
    val resultMax = basics.Math.getPrivateMember

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
    val numbers = List(10, 20, 30)
    val numbersNotMatch = List(10, 20, 30, 43)

    val num = matchNumberList(numbers)
    val num2 = matchNumber2List(numbers)
    val num3 = matchNumber3List(numbersNotMatch)
    val num4 = matchNumber2List(numbersNotMatch)

    assert(num == 20, true)
    assert(num2 == 20, true)
    assert(num3 == 20, true)
    assert(num4 == -1, true)
  }

  test("pattern match 4") {
    // type matching
    val randomStuff = List(
      NoPrize(),
      Car("Tesla"),
      Car("Civic"),
      Cash("Mula"),
      Trip("Covid19 NO VACAY"),
      NoPrize(),
      Trip("Peru")
    )

    val result = randomStuff.take(1)(0) match {
      case t: Trip => "You have won a trip"
      case c: Car => "You won a car"
      case ca: Cash => "You won cash"
      case n: NoPrize => "SOL, go home!"
    }

    assert(result == "SOL, go home!", true)
  }

  test("pattern match 5: Guarding") {
    val importantContacts = Set("user1@email.com")
    val importantEmail = Email("user1@email.com", "user1 sent message")
    val importantEmailNOT = Email("user2@email.com", "user2 sent message")

    def alertOrNoAlert(email: Email) = email match {
      case Email(from, body) if importantContacts.contains(from) => body
      case Email(_, _) => "do not disturb I"
    }

    assert(alertOrNoAlert(importantEmail) == "user1 sent message", true)
    assert(alertOrNoAlert(importantEmailNOT) == "do not disturb I", true)
  }

  test("Collections: Lists") {
    // all of these immutable List will return a new list upon operating on them
    val numbers = List(1, 2, 3, 4)
    assert(numbers.head == 1, true)
    assert(numbers.tail == List(2, 3, 4), true)
    assert(numbers.init == List(1, 2, 3), true)
    assert(numbers.last == 4, true)

    assert(numbers :+ 5 == List(1, 2, 3, 4, 5), true)
    assert(0 +: numbers == List(0, 1, 2, 3, 4), true)

    assert(numbers ++ List(5, 6, 7) == List(1, 2, 3, 4, 5, 6, 7), true)
    assert(List(-1, 0) ++ numbers == List(-1, 0, 1, 2, 3, 4), true)

    assert(numbers.drop(1) == List(2, 3, 4), true)
    assert(numbers.drop(2) == List(3, 4), true)
    assert(numbers.drop(3) == List(4), true)
    assert(numbers.drop(numbers.size) == List(), true)
    assert(numbers.dropRight(1) == List(1, 2, 3), true)
    assert(numbers.dropRight(2) == List(1, 2), true)
    assert(numbers.dropRight(3) == List(1), true)
    assert(numbers.dropRight(numbers.size) == List(), true)
    assert(numbers.dropWhile(_ < 3) == List(3, 4), true)
    assert(numbers.dropWhile(_ < 2) == List(2, 3, 4), true)
    assert(numbers.dropWhile(_ < 1) == List(1, 2, 3, 4), true)
  }

  test("Collections: Set") {
    val numbers = Set(1, 1, 2, 2, 3, 3, 4, 4, 4)
    assert(numbers == Set(1, 2, 3, 4), true)

    assert(numbers + 6 == Set(1, 2, 3, 4, 6), true)
    assert(numbers - 1 == Set(2, 3, 4), true)
    assert(numbers - 2 == Set(1, 3, 4), true)

    assert(numbers ++ Set(0, 10) == Set(0, 10, 1, 2, 3, 4), true)
  }

  test("Collections: Map") {
    val Sunday = "Sunday"
    val Monday = "Monday"
    val Tuesday = "Tuesday"
    val Wednesday = "Wednesday"
    val Thursday = "Thursday"
    val Friday = "Friday"
    val Saturday = "Saturday"

    val weekdays = Map(
      0 -> Sunday,
      1 -> Monday,
      2 -> Tuesday,
      3 -> Wednesday,
      4 -> Thursday,
      5 -> Friday,
      6 -> Saturday,
    )

    val sortedWeekdays = SortedMap(
      0 -> Sunday,
      1 -> Monday,
      2 -> Tuesday,
      3 -> Wednesday,
      4 -> Thursday,
      5 -> Friday,
      6 -> Saturday,
    )

    assert(weekdays(1) == Monday, true)
    assert((weekdays + (7 -> "Not A WeekDay")).size == 8, true)
    assert((weekdays - 1).size != 7, false)
    assert((weekdays - 1).size == 6, true)

    weekdays.foreach(day => println(s"${day._1} >-< ${day._2}"))
    print("\nSorted Weekdays \n")
    sortedWeekdays.foreach(day => println(s"${day._1} >-< ${day._2}"))
  }

  test("Collections: Tuple") {
    val tuple = (10, 20, 30)
    assert(tuple._1 == 10, true)
    assert(tuple._2 == 20, true)
    assert(tuple._3 == 30, true)
  }

  test("Collections: Operations") {
    // operations on collections are available to all types of collections i.e. List, Set, Map, etc.
    val numbers = List(2, 3, 1)
    assert(numbers.sum == 6, true)
    assert(numbers.product == 6, true)
    assert(numbers.min == 1, true)
    assert(numbers.max == 3, true)

    assert(numbers.filter(num => num < 3) == List(2, 1), true)
    assert(numbers.filter(_ < 3) == List(2, 1), true)
    assert(numbers.filter(_ < 3).min == 1, true)
    assert(numbers.filter(_ < 3).max == 2, true)
  }

  test("Collections: Transformation") {
    // operations for converting from one type of collection to the other
    case class Event(location: String, dayOfWeek: String, sessionTimeInSeconds: Int, source: String)

    val event1 = Event("US", "Sunday", 10, "twitter")
    val event2 = Event("Japan", "Monday", 10, "twitter")
    val event3 = Event("England", "Sunday", 10, "twitter")
    val event4 = Event("Philippines", "Monday", 10, "twitter")
    val event5 = Event("Mexico", "Sunday", 10, "twitter")

    val events = List(event1, event2, event3, event4, event5)
    //    val locations = events.map(eventItem => eventItem.location)
    val locations = events.map(_.location)

    assert(locations.isInstanceOf[List[String]], true)
    assert(locations.size == events.size, true)
  }

  test("Collections: Flatmap") {
    val list = List(List(1, 2, 3, 4), List(10, 21, 32, 43))
    val singleList = list.map(list => list.map(_ + 1)).flatten
    val singleListWithFlatMap = list.flatMap(list => list.map(_ + 1))

    assert(singleList == List(2, 3, 4, 5, 11, 22, 33, 44), true)
    assert(singleListWithFlatMap == List(2, 3, 4, 5, 11, 22, 33, 44), true)
  }

  test("Collections: Flatmap and Option") {
    def convertToInt(s: String): Option[Int] = {
      try {
        Some(s.toInt)
      } catch {
        case e: NumberFormatException => None
      }
    }

    val arguments = List("10", "eight", "5")

    assert(arguments.map(num => convertToInt(num)) == List(Some(10), None, Some(5)), true)
    assert(arguments.map(convertToInt(_)) == List(Some(10), None, Some(5)), true)
    assert(arguments.map(convertToInt) == List(Some(10), None, Some(5)), true)

    assert(arguments.flatMap(convertToInt) == List(10, 5), true)
    assert(arguments.map(convertToInt).flatten.sum == 15, true)
    assert(arguments.flatMap(convertToInt).sum == 15, true)
  }

  test("Concurrency: Future") {
    val fut = Future {
      Thread.sleep(10000)
      21 + 21
    }
    // fut will complete after few seconds and return 42
    // fut.isCompleted returns if future is completed
    // when fut is completed you can get value by using fut.value

    fut.onComplete({
      case Success(result) => assert(result == 42, true)
      case Failure(e) => assert(e != "42", false)
    })
  }

  test("Concurrency: Future Transformation") {
    val salary = Future {
      Thread.sleep(20000)
      4000
    }

    val bonus = 500
    val salaryWithBonus = salary.map(value => value + 500)

    def getTotal = {
      val productPrice = Future {
        Thread.sleep(500); 150
      }
      val prodTax = Future {
        Thread.sleep(500); 5.50
      }
      for {
        price <- productPrice
        tax <- prodTax
      } yield price + tax
    }

    Future {
      Thread.sleep(6500)
      assert(getTotal == 15.50, true)
    }
  }

  test("Concurrency: Future filter") {
    val salary = Future {
      Thread.sleep(20000)
      4000
    }

    val largeSalary = salary.filter(_ > 5000)

    //all in one
    val salaryFuture = Future {
      Thread.sleep(5400)
      3000
    }

    val incrementedSalary = salaryFuture.collect { case salary if salary < 5000 => salary + 1000 }
  }

  test("Concurrency: Promises with success/failure and failure with recovery with fallback futures") {
    val promise = Promise[Int]

    promise.future
    promise.future.value
    promise.success(100)

    val failedFuture = Future { 10 / 0 }

    failedFuture.value

    val failedException = failedFuture.failed


    val fallbackFuture = Future.successful(100)
    val computation = Future { 1 / 0} fallbackTo fallbackFuture
  }


}
