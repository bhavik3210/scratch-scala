package exercises

import scala.annotation.tailrec

object Util {
  def printPagesForPDFTKTool(pagesToBeExcluded: String): String = {
    val listOfPages = pagesToBeExcluded.split(",")

    def calculateResultCopy(currentNumber: Int, result: String, skipCurrentDigit: Boolean): String = {
      if (currentNumber > 0) {
        if (skipCurrentDigit) {
          s"$result-${currentNumber - 1} "
        } else {
          if (result.nonEmpty && currentNumber > 0 && result.charAt(result.length - 1) == ' ') {
            s"$result$currentNumber"
          } else {
            if (currentNumber == 1) s"$result$currentNumber"
            else result
          }
        }
      } else result

    }

    @tailrec
    def helper(n: Int, result: String): String = {
      if (n > 107) result
      else {
        if (listOfPages.contains(n.toString)) helper(n + 1, calculateResultCopy(n, result, true))
        else helper(n + 1, calculateResultCopy(n, result, false))
      }
    }

    helper(0, "")
  }
}
