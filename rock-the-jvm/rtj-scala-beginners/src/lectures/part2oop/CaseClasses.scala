package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hasCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  // println(instance) == println(instance.toString) // syntactic sugar
  println(jim)

  // 3. equals and hasCode implemented Out of box
  val jim2 = new Person("Jim", 34)
  val jim3 = new Person("Jims", 34)
  println(jim == jim2)
  println(jim.hashCode())
  println(jim2.hashCode())
  println(jim3.hashCode())

  // 4. CCs have handy copy method
  val jim4 = jim.copy(age = 45)
  println(jim4)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // useful in Akka

  // 7. CCs have extractor patterns = CCs can be used in Pattern Matching

  // 8. there is also case object
  case object UK {
    def name: String = "United Kingdom"
  }

}
