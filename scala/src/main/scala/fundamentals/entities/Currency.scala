package fundamentals.entities

object Currency {
  private val exRates: Map[String, Double] = Map(
    "CAD" -> 0.90,
    "USD" -> 0.68,
    "EUR" -> 0.59
  )

  implicit def stringToCurrency(money: String): Currency = {
    val Array(value: String, code: String) = money.split("\\s")
    val requestedAmount: Double = value.toDouble
    val currencyFactor: Double = exRates(code)
    val totalCost: Int = (requestedAmount / currencyFactor).toInt

    println(s"Cost of converting $requestedAmount $code -> $totalCost")
    Currency(code, value.toDouble, MoneyAmount(totalCost))
  }
}

case class Currency(code: String, amount: Double, costInMoney: MoneyAmount)