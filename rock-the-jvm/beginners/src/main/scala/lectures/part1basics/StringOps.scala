package lectures.part1basics

object StringOps extends App {
  val str: String = "Hello, I am learning scala."
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)

  val aNumbersString = "45"
  val aNumber = aNumbersString.toInt
  println('a' +: aNumbersString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // scala-specific: String interpolators

  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"hello, my name $name, and i am $age years old."
  val greeting2 = s"hello, my name $name, and i will be turning ${age + 1}"

  // F-interpolators
  val speed = 1.2f
  val myth = f"name can eat $speed%2.2f burgers per minute"
  println(myth)

  // raw-interpolator
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped")
}
