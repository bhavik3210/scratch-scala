package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by VALUE: " + x)
    println("by VALUE: " + x)
  }

  //equivalent to
  //  def calledByValue(40617842083487): Unit = {
  //    println("by VALUE: " + "40617842083487")
  //    println("by VALUE: " + "40617842083487")
  //  }

  def calledByName(x: => Long): Unit = {
    println("by NAME: " + x)
    println("by NAME: " + x)
  }

  //equivalent to
  //  def calledByName(System.nanoTime()): Unit = {
  //    println("by NAME: " + System.nanoTime())
  //    println("by NAME: " + System.nanoTime())
  //  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime()) //that's why different time shows up for by name


  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite(), 34) //this will crash
  printFirst(34, infinite()) //why not crash: Infinite is not called at all

}
