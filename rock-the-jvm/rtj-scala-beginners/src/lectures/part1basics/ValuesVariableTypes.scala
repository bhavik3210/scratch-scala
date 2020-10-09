package lectures.part1basics

object ValuesVariableTypes extends App {
  /**
   * val is immutable (preferred scala fp way)
   * compiler can also Infer types if not explicitly declared
   */
  //
  val x: Int = 32 //equivalent to:  val x = 32 (compiler Infer types)
  println(x)

  val aString: String = "hello"
  val bString = "goodbye"

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 233
  val aLong: Long = 2342343243243243242L
  val aFloat: Float = 2.0f
  val aDouble: Double = 23.23

  /**
   * var is mutable (but avoid usage as much as possible)
   */
  var aVariable: Int = 3
  aVariable = 23 //side effects
}
