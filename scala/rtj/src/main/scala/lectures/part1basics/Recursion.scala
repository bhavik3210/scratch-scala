package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): BigInt = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  println(factorial(10))
  //println(factorial(10000)) //stackoverflow (use tail recursion)

  /**
   * Tail Recursive: use recursive call as the LAST expression
   */
  def anotherFactorialWithLessOverhead(n: Int): BigInt = {
    @tailrec //compiler will throw an error if this is not tail recursive and the is annotated as such
    def factorialHelper(x: Int, result: BigInt): BigInt = {
      if (x <= 1) result
      else factorialHelper(x - 1, x * result) //called tail recursion
    }

    factorialHelper(n, 1)
  }

  //  println(anotherFactorialWithLessOverhead(10))
  //  println(anotherFactorialWithLessOverhead(10000))

  /**
   * BEST PRACTICE: WHEN YOU NEED LOOPS, USE tail recursion
   */

  //1. concatenate a string n times using tail recursion
  def repeatWords(n: Int, copy: String): String = {
    def helper(n: Int, result: String): String =
      if (n <= 1) result
      else helper(n - 1, s"$copy $result")

    helper(n, copy)
  }

  println(repeatWords(6, "test"))

  //2. IsPrime function using tail recursion
  def isPrime(n: Int): Boolean = {
    def helper(x: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (x <= 1) true
      else helper(x - 1, n % x != 0 && isStillPrime)
    }

    helper(n / 2, true)
  }

  println(isPrime(24))
  println(isPrime(37))

  //3. isFibbo using tail recursion and auxilary function
  def fibo(n: Int): BigInt = {
    def helper(x: Int, result1: BigInt, result2: BigInt): BigInt = {
      if (x >= n) result1
      else helper(x + 1, result1 + result2, result1)
    }

    if (n <= 1) 1
    else helper(2, 1, 1)
  }

  println(fibo(8))
}
