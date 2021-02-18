package lectures.part3fp

object AnonymousFunctions extends App {

  val doublerOOWay = new Function1[Int, Int] {
    override def apply(incomingParameter: Int): Int = incomingParameter * 2
  }

  // equivalent to above and non "oo" way of doing things and scala/fp way of doing it
  val doubler = (x: Int) => x * 2 //known as anonymous function or (LAMBDA)
  val doublerDifferentWay: Int => Int = x => x * 2 // but use the one above

  // multiple parameters
  val adder = (a: Int, b: Int) => a + b
  val adderDifferentWay: (Int, Int) => Int = (a, b) => a + b
  println(adder)
  println(adder(2, 3))


  // no params
  val justDoSomething = () => 3
  val justDoSomethingDifferent: () => Int = () => 3

  println(justDoSomethingDifferent) // function itself, prints: lectures.part3fp.AnonymousFunctions$$$Lambda$10/1012570586@47f37ef1
  println(justDoSomethingDifferent()) // call to a function, prints: 3

  // curly braces with lambdas
  val stringTOInt = { (str: String) =>
    str.toInt
  }

  // syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to (x: Int) => x + 1 or x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  val superAddCurriedFunction = (x: Int) => (y: Int) => x + y
  println(superAddCurriedFunction(3)(4))
}
