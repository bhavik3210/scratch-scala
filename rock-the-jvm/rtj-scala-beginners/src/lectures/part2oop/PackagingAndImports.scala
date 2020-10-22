package lectures.part2oop

// import playground.{PrinceCharming, Cinderella => Princess}
// import playground._ : to import everything

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // pakcage members are accessible by their simple name or use fully qualified name with packages
  val writer = new Writer("Daniel", "Rock The JVM", 2018)

  // package object (rarely used in practice)
  sayHello
  println(SPEED_OF_LIGHT)

  // 1. use Fully qualified names
  val date = new Date()
  //2. use alias
  val sqlDate = new SqlDate(2019, 2, 3)

  // default imports
  // java.lang - String, Object, Exception, etc.
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
