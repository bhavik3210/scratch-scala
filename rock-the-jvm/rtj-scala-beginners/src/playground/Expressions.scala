package playground

object Expressions extends App {
  val x = 1 + 2 // expression
  println(x)

  /**
   * Operators:  + - * / & | ^ << >> >>> (right shift with zero extension)
   */
  println(2 + 3 * 4)
  println(1 == x) // == != > <= >=
  println(!(1 == x)) // ! && ||

  var aVariable = 2
  aVariable += 3 //also works with -= *= /= ....side effects
  println(aVariable)

  /**
   * Instructions: tell computer to do something (doing something)
   * VS
   * Expressions: has a value (all expressions compute a value)
   */

  // IF expression
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3 // called "if expression"
  println(aConditionValue)
  println(if(aCondition) 5 else 3)
  println(1 + 3)

  /**
   * DO NOT DO THIS IN SCALA (do not use while loops in scala as shown below)
   * Prefer to use expression for everything in scala
   */
  var i = 0
  while (i < 10) {
    println(i)
    i+=1
  }

  val aWeirdValue = (aVariable = 3) // returns Unit === void in java
  println(aWeirdValue)
  // side effects: println(), whiles, reassigning, try to avoid these everywhere in scala

  /**
   * Code Blocks: are Expression
   * All values that are within codeblocks are not visible outside of the block
   */
  val aCodeBlock = { //this is still an expression since it yields some valid value and not Unit type
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye" // last expression value will be returned of the block
  }
  println(aCodeBlock)
}
