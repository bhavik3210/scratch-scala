import scala.math.E
import scala.language.postfixOps

println("================================= Types =================================")
println("=========================================================================")
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


println("================================= interpolator =================================")
println("================================================================================")
val color = "White"
val price = 10.23

println("The Price for " + color + " poster is " + price)
println(s"The Price for $color poster is $price")
println(s"The Price for ${color} poster is ${price.toString}")

println("=============== raw interpolator ================")
println("treat escape literals \\ \\ \\")
println(raw"treat escape literals \\ \\ \\")

println(E)
println(f"${E}%.2f")




println("================================= method operations =================================")
println("=====================================================================================")
val i1 = 10
val i2 = 30

val sum = i1 + i2
val sum = i1.+(i2)

val s = "hello"
s indexOf ('o')
s indexOf ('0')

println("=============== prefix operators ================")
val negative = -100

println("=============== postfix operators ================")
val long = 100 toLong




println("================================= create your own prefix operator =================================")
println("===================================================================================================")
class TextLabel(val string: String) {
  def unary_! = new TextLabel(string + "!!")

  override def toString: String = string
}

val text = new TextLabel("wow with double bang")
!text
text toString





println("================================= creating your own value class =================================")
println("=================================================================================================")
class Quantity(val n: Int) extends AnyVal {
  def +(q: Quantity): Quantity = new Quantity(n + q.n)
}

val q1 = new Quantity(10)
val q2 = new Quantity(20)

val q3 = q1 + q2
q3.n



println("======================================== function literal ===========================================")
println("=====================================================================================================")
val multiply = (a: Int, b: Int) => a * b

println("=============== functional value is an object ================")
multiply

println("=============== function value is a function ================")
multiply(2, 10)




println("========================================= partially applied functions =========================================")
println("================================================================================================================")
def addSum(a: Int, b: Int, c: Int) = a + b + c

println("=============== when all arguments are supplied ================")
val s1 = addSum(1, 2, 3)

println("=============== when one argument is not supplied ================")
val s2 = addSum(_: Int, 2, 3)
val s3 = addSum(1, _: Int, 3)
val s4 = addSum(1, 2, _: Int)

s1
s2(1)
s3(2)
s4(3)



println("======================================= when 2 arguments are not applied =======================================")
println("================================================================================================================")
val s5 = addSum(_: Int, _: Int, 3)
val s6 = addSum(1, _: Int, _: Int)
s5(1, 2)
s6(2, 3)

println("=============== when no arguments are applied ================")
val s7 = addSum(_:Int, _:Int, _:Int)
println("=============== underscore represent the entire parameter list ================")
val s8 = addSum _

s7(1,2,3)
s8(1,2,3)

