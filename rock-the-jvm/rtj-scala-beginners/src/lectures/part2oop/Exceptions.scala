package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  //  println(x.length) will crash with npe

  // throwing and catching expressions
  //  throw new NullPointerException
  //  val aWeirdValue = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes
  // Exception: denote something wrong with program
  // Error: something wrong with system, i.e stackoverflow on jvm

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("no int for you!")
    else 33

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will executed no matter what
    // optional
    // doesn't influence return type of this expression
    // use finally ONLY for side effects
    println("FINALLY")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception

  val exception = new MyException

  //  throw exception will throw your own
}
