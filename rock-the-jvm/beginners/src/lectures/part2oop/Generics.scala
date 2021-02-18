package lectures.part2oop

object Generics extends App {

  class MyList[A] {
    // use the type A
  }

  trait MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
//    def empty[A]: MyList[A] = ???
//    def add[B >: A](element: B): MyList[B] = ??? // if a supertype of B is added onto the list of A then the list will turn into A
  }

//  val emptyListOfIntegers = MyList.empty[Int]

//  println(emptyListOfIntegers.getClass)

  // variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog) ???

  // 2. NO = INVARIANCE
  class InvariantList[A]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, NO! | CONTRAVARIANCE
  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // 3. BUT | CONTRAVARIANCE
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal] //valid

  // bounded types (upper and lower) < or >
  class Cage[A <: Animal](animal: A) // cage only accept that is a Subtype of Animal class
//  class Cage[A >: Animal](animal: A) // cage only accept that is a Supertype of Animal class

  val cage = new Cage(new Dog)

  class Car

  //  val newCage = new Cage(new Car) // wrong won't compile


}
