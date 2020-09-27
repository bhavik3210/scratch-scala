
package lectures

object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b //string concatenation

  def aParameterlessFunction(): Int = 32

  println(aParameterlessFunction())
  println(aParameterlessFunction)


  /**
   *
   * When you need loops in scala, use recursion (like u used to in SML)
   * with recursive function return type is explicitly and required
   */
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // Side effect
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallLocalFunction(a: Int, b: Int): Int = a * b

    aSmallLocalFunction(n, n)
  }

  def greet(name: String, age: Int): String = {
    s"Hi, my name is $name and I am $age years old."
  }

  println(greet("John", 66))

  def factorial(n: Int): Int = {
    if (n == 1) 1
    else n * factorial(n - 1)
  }

  println(factorial(5))

  def fibbo(n: Int): Int = {
    if (n <= 2) 1
    else fibbo(n - 1) + fibbo(n - 2)
  }

  println(fibbo(8))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n/2)
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(24))
}
