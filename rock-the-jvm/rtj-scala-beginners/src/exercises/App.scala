
package exercises

object App extends App {

  // pages that include scala rocks

  val pagesToBeExcluded = "3,7,11,16,20,23,26,32,35,38,41,44,47,50,54,58,61,65,68,71,74,76,81,93,96,101,103,105,107"
  val listOfPages = pagesToBeExcluded.split(",")

  def asdf(): String = {
    def wer(lastDigit: Int, currentDigit: Int, result: String, skipCurrentDigit: Boolean): String = {
      if(lastDigit == 0) {
        result
      } else if(lastDigit < currentDigit) {
        if (skipCurrentDigit) {
          s"$result"
        } else {
          s"$result, $lastDigit-$currentDigit"
        }
      } else result
    }

    def temp(currentNumber: Int, result: String, skipCurrentDigit: Boolean): String = {

      // "", "1-", "1-2," , "1-2, 4-"
      // "", "1", "1-2" , "1-2, 4-"

      //"" => "1" => add "-2, " => "4" => "-6,"

      //1-2,
      if (currentNumber > 0) {
        if(skipCurrentDigit) {
          s"$result-${currentNumber-1}, "
        } else {
          s"$result$currentNumber"
        }
      } else result

    }

    def helper(n: Int, result: String): String = {
        if (n > 107) result
        else {
//          if(listOfPages.contains(n.toString)) {
//            helper(n+1, n+1, wer(lastDigit, n, result, false ))
//          } else {
//            helper(n+1, n, wer(lastDigit, n, result, true))
//          }
          if (listOfPages.contains(n.toString)) {
            helper(n+1, temp(n,result,true))
          } else {
            helper(n+1, temp(n,result, false))
          }
          // 1
          // 1
          // 1-(3-1),
        }
    }
    helper(0, "")
  }

  println(asdf())
}


//def helper(start: Int, result: String, break: Boolean): String = {
//if (start > 107) result
//else {
//if(listOfPages.contains(start.toString)) {
//helper(start+1, s"$result")
//} else {
//helper(start + 1, s"$result,$start")
//}
//// 1
//// 1
//// 1-(3-1),
//}
//}
//helper(start, result).toString



//def asdf(start: Int, result: String): String = {
//  def wer(break: Boolean, n: Int, result: String): String = {
//  if (break) {
//  s"$result-${n-1},"
//} else {
//  s"$result"
//}
//}
//
//  def helper(n: Int, result: String): String = {
//  if (n > 107) result
//  else {
//  if(listOfPages.contains(n.toString)) {
//  helper(n+1, wer(true, n, result))
//} else {
//  helper(n+1, wer(false, n, result))
//}
//  // 1
//  // 1
//  // 1-(3-1),
//}
//}
//  helper(start, result).toString
//}