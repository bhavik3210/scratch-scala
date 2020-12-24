package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // Problem: OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B] // goes from 1 to 22 so FunctionX type can take up to 22 parameters
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("3") + 5)

  //  val adder = new Function2[Int, Int, Int] {
  //    override def apply(v1: Int, v2: Int): Int =
  //  } //equivalet to below function
  val adder = new ((Int, Int) => Int) {
    override def apply(v1: Int, v2: Int): Int = ???
  }
  // Function types Function2[A, B, R] === (A,B) => R
  // ALl SCALA FUNCTIONS ARE ESSENTIALLY OBJECTS because JVM was designed with OOP in mind


  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function which takes an int and returns another function which takes an int and returns an int
      - What's the type of this function
      - How to do it
   */


  // 1. a function which takes 2 strings and concatenates them
  val concatenator = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = s"$v1 $v2"
  }
  println(concatenator("first", "last"))

  // 3.
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}

class Action {
  def execute(element: Int): String = ???
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
