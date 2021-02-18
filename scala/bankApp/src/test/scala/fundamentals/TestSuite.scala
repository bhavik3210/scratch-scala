package fundamentals

import org.scalatest.funsuite.AnyFunSuite

class TestSuite extends AnyFunSuite{
  test("test employee class and companion object") {

    val employeeA = Employee("first", "last", 1)
    assert(employeeA.toString == "first last 1", true)
  }
}
