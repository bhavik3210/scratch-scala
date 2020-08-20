object UtilFunctions {

  //explicit return type
  def plusOneOrZero(number: Int): Int = {
    if (number <= 0) 0 else number + 1
  }

  //inferred return type
  def product(a: Int, b: Int) = {
    a * b
  }

  def productShort(a: Int, b: Int) = a * b
}
