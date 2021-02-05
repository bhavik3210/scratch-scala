
package exercises

import exercises.Util.printPagesForPDFTKTool

object App extends App {
  val pagesToBeExcluded = "3,7,11,16,20,23,26,32,35,38,41,44,47,50,54,58,61,65,68,71,74,76,81,93,96,101,103,105,107"
  println(printPagesForPDFTKTool(pagesToBeExcluded))
}