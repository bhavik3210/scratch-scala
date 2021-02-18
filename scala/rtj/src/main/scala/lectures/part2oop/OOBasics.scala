package lectures.part2oop

object OOBasics extends App {

  val person = new Person("Bhavik", 1000)
  println(person.age)
  println(person.x)
  person.greet("John")
  person.greet()


  val author = new Writer("Charles", "Dickens", 1812)
  val imposterAuthor = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)
  println(novel.authorAgeAtBookReleaseYear)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposterAuthor)) //because memory comparison

  val counter = new Counter

  /**
   * all of these methods in counter class doesn't manipulate the value inside the
   * object, instead they return a new object with the new manipulated value, immutability
   */
  counter.incrementByOne.print
  counter.incrementByOne.incrementByOne.incrementByOne.print
  counter.increment(10).print
}

// class parameters are NOT FIELDS i.e. name
// in order to convert parameters to fields use keyword val i.e. age
// constructor
class Person(name: String, val age: Int) {
  //body
  val x = 2
  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  def greet(): Unit = println(s"$name says: Hi") //overloading with different signatures

  //multiple constructors
  def this(name: String) = this(name, 0) //but you can just assign default value to age = 0 in the class parameters to achieve this easily
  def this() = this("John Doe")

}

class Writer(first: String, last: String, val birthYear: Int) {
  def fullname = s"$first $last"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAgeAtBookReleaseYear: Int = yearOfRelease - author.birthYear

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Counter(val value: Int = 0) {
  def currentCount: Int = value

  def incrementByOne = new Counter(value + 1)

  def decrementByOne = new Counter(value - 1)

  def increment(incrementBy: Int): Counter = {
    if (incrementBy <= 0) this
    else incrementByOne.increment(incrementBy - 1)
  }

  def decrement(decrementBy: Int): Counter = {
    if (decrementBy <= 0) this
    else decrementByOne.decrement(decrementBy - 1)
  }

  def print = println(value)
}