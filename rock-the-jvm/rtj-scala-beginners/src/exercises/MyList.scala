package exercises

abstract class MyList {
  val head: Int
  val tail: MyList

  def isEmpty: Boolean

  def add(element: Int): MyList

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}


object Empty extends MyList {
  val head: Int = -1
  val tail: MyList = this

  def isEmpty: Boolean = true

  def add(element: Int): MyList = new Cons(element, Empty)

  override def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  val head: Int = h
  val tail: MyList = t

  def isEmpty: Boolean = false

  def add(element: Int): MyList = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + head
    else h + " " + t.printElements
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.toString)
}