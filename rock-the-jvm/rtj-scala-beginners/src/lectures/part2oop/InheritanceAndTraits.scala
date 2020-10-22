package lectures.part2oop

object InheritanceAndTraits extends App {

  class Animal {
    val creatureType: String = "Wild"

    //    final def eat = println("nom nom")
    def eat = println("nom nom") //prevents overriding
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  //constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  //  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType = "Domestic"
    override def eat = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  //overriding vs overloading

  //super

  //preventing overrides
  //1 - use final keyword modifier before function on member
  //2 - use final keyword on the entire class, prevents entire class from being extended
  //3 - keyword sealed: seal the class = extend classes in THIS FILE only, but prevents extension in other files
}
