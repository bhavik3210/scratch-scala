package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "Wild"

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    def eat: Unit = println("crunch crunch") //override keyword not necessary
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit

    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    def eat: Unit = "nom nom nom"

    def eat(animal: Animal): Unit = println(s"I'm a crock and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1: traits cannot have construction parameters
  // 2: multiple traits may be inherited by same class
  // 3: traits are behavior, abstract class are type of thing
  // use traits when describing what an object CAN DO vs what object is
  // use abstract when describing what an object is vs What it can do

}

