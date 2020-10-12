package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangoutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} ${person.name} are together." // infix notation

    def unary_! : String = s"$name, what the heck?" //prefix notation

    def isAlive: Boolean = true //postfix notation

    def apply(): String = s"Hi my name is $name and I Like $favoriteMovie"
  }

  val mary = new Person("Mary", "Interstellar")
  mary.likes("Interstellar")
  // infix notation = operator notation, only works with single parameter
  mary likes "Interstellar" //syntactic sugar

  //"operators" in scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangoutWith tom) //hangoutWith acts like an operator between mary and tom
  // you can also use literal + - or other characters inplace of method name
  println(mary + tom)
  // akka actors have ! ? etc. as method names

  //ALL OPERATORS are METHODS.
  println(1.+(2))
  println(1 + 2)


  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val sameAsX = 1.unary_-
  // unary_ prefix only work with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply (special case and more used in scala)
  // when object gets called with () it will look for apply method within its class definition
  println(mary.apply())
  println(mary()) // equivalent to .apply() above

}
