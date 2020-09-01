import scala.math.E
import scala.language.postfixOps

val minByteValue: Byte = -128
val maxByteValue: Byte = 127

val minShortValue: Short = -32768
val maxShortValue: Short = 32767

val ten = 10

val tenLong = 10L
val tenLongVariation = 10l

val tenFloat = 10F
val anotherTenFloat = 10f

val tenDouble = 10.23
val anotherTenDouble = 10.23D
val anotherTenDoubleSmallD = 10.23d
val tenDoubleExpo = 1.023E1

val character = 'c'

val helloWorld = "Hello world"
val escapeWorld = "\\\"\'"
val noEscapeWorld = """\"'"""

val isTrue = true
val isFalse = false

// interpolator
val color = "White"
val price = 10.23

println("The Price for " + color + " poster is " + price)
println(s"The Price for $color poster is $price")
println(s"The Price for ${color} poster is ${price.toString}")

// raw interpolator
println("treat escape literals \\ \\ \\")
println(raw"treat escape literals \\ \\ \\")

println(E)
println(f"${E}%.2f")

// method operations
val i1 = 10
val i2 = 30

val sum = i1 + i2
val sum = i1.+(i2)

val s = "hello"
s indexOf ('o')
s indexOf ('0')

// prefix operators
val negative = -100

// postfix opeartor
val long = 100 toLong

// create your own prefix operator
class TextLabel(val string: String) {
  def unary_! = new TextLabel(string + "!!")

  override def toString: String = string
}

val text = new TextLabel("wow with double bang")
!text
text toString

// creating your own value class
class Quantity(val n: Int) extends AnyVal {
  def +(q: Quantity) = new Quantity(n + q.n)
}

val q1 = new Quantity(10)
val q2 = new Quantity(20)

(q1 + q2).n

