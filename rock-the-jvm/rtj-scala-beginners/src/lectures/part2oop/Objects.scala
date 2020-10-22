package lectures.part2oop

object Objects extends App {

  // Scala does not have class-level functionality ("static")
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    //factory method
    def apply(mother: Person, father: Person, name: String): Person = new Person(name)
  }

  class Person(val name: String) {
    // instance-level functionality
  }

  // Concept: Companions
  /*
   So object and class of Person are companions. Everything static goes into
   object (singleton instance) and instance (regular instance) based things go into class.
   You can have multiple instance of class Person but there is also a singleton instance
   Person that is represented through declaration of object Person
   */

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  // Scala instance = NOT SINGELETON INSTANCE
  val sam = Person(mary, john, "Sam") //apply factory method will let you avoid new keyword and apply
  val dean = Person(mary, john, "Dean") //apply factory method will let you avoid new keyword and apply

  // Scala Applications == Scala Object with
  // def main(args: Array[String]): Unit - this is for JVM entry point same as java


}
