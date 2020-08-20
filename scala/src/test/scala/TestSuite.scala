import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer

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
    assert(numbersAndLettersCombo.size == numbers.size*letters.size, true)
  }

  test("test functional loops (yield)") {
    val letters = List("a", "b", "d", "e", "f")
    val numbers = List(1, 2, 3, 4)

    //returns a new immuatable copy
    val numbers3x = for (number <- numbers) yield { number * 3 } //braces are optionals for single
    assert(numbers3x.size == numbers.size, true)
    assert(numbers3x.head == 3, true)
    assert(numbers3x(1) == 6, true)
    assert(numbers3x(2) == 9, true)
    assert(numbers3x(3) == 12, true)
    assert(numbers3x.head != 1, true)
    assert(numbers3x(1) != 2, true)
    assert(numbers3x(2) != 3, true)
    assert(numbers3x(3) != 4, true)

    val evenNumbers3x = for (number <- numbers if(number % 2 == 0)) yield number * 3
    assert(evenNumbers3x.size != numbers.size, true)
    assert(evenNumbers3x.head == 6, true)
    assert(evenNumbers3x(1) == 12, true)
    assert(evenNumbers3x.head != 2, false)
    assert(evenNumbers3x(1) != 4, false)

    val numbersAndLetters = for {
      number <- numbers
      letter <- letters
    } yield s"$number => $letter"
    assert(numbersAndLetters.size == numbers.size*letters.size, true)
  }

  test("test basic function") {
    val negativeNumber = UtilFunctions.plusOneOrZero(-1)
    val zeroNumber = UtilFunctions.plusOneOrZero(0)
    val positiveNumber = UtilFunctions.plusOneOrZero(4)

    assert(negativeNumber == 0, true)
    assert(negativeNumber != -1, true)
    assert(zeroNumber == 0, true)
    assert(positiveNumber == 5, true)
    assert(positiveNumber != 4, true)

    val zeroNumberProduct = UtilFunctions.product(0, 4)
    val positiveNumberProduct = UtilFunctions.productShort(4, 2)
    val productAnonymous = UtilFunctions.productAnnonymous(4, 2)

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




}
