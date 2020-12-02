package exercises

abstract class MyList[+A] {
  val head: A
  val tail: MyList[A]

  def isEmpty: Boolean

  // handling covariance situation
  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}


object Empty extends MyList[Nothing] {
  val head: Nothing = ???
  val tail: MyList[Nothing] = ???

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  val head: A = h
  val tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + head
    else h + " " + t.printElements
}

object ListTest extends App {
//  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(list.tail.head)
//  println(list.add(4).head)
//  println(list.toString)
//
//  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  val listOfStrings: MyList[String] = new Cons("Hello", new Cons(",", new Cons("World", Empty)))
//
//  println(listOfIntegers.toString)
//  println(listOfStrings.toString)
}