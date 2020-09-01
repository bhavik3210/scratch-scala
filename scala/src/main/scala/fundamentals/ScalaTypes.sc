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

// function literal
val multiply = (a: Int, b: Int) => a * b

multiply // functional value is an object

multiply(2, 10) //function value is a function

// partially applied functions
def addSum(a: Int, b: Int, c: Int) = a + b + c

// when all arguments are supplied
val s1 = addSum(1, 2, 3)

// when one argument is not supplied
val s2 = addSum(_: Int, 2, 3)
val s3 = addSum(1, _: Int, 3)
val s4 = addSum(1, 2, _: Int)

s1
s2(1)
s3(2)
s4(3)

// when 2 arguments are not applied
val s5 = addSum(_: Int, _: Int, 3)
val s6 = addSum(1, _: Int, _: Int)
s5(1, 2)
s6(2, 3)

// whn no arguments are applied
val s7 = addSum(_:Int, _:Int, _:Int)
// underscore represent the entire parametner list
val s8 = addSum _

s7(1,2,3)
s8(1,2,3)