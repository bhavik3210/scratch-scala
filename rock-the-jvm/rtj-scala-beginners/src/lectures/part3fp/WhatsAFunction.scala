package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // PROBLEM: OOP world

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B] // goes from 1 to 22 so FunctionX type can take up to 22 parameters
  val stringToIntConverter = new Function[String, Int] {
    override def apply(value: String): Int = value.toInt
  }

  println(stringToIntConverter("3") + 3)


  //  val adder = new Function2[Int, Int, Int] {
  //    override def apply(v1: Int, v2: Int): Int =
  //  } //equivalet to below function
  val adder = new ((Int, Int) => Int) {
    override def apply(v1: Int, v2: Int): Int = ???
  }
  // Function types Function2[A, B, R] === (A,B) => R
  // ALl SCALA FUNCTIONS ARE ESSENTIALLY OBJECTS because JVM was designed with OOP in mind

  // 1. a function which takes 2 strings and concatenates them
  val concater = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = s"$v1 $v2"
  }
}

class Action {
  def execute(element: Int): String = ???
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
