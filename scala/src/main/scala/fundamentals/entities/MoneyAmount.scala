package fundamentals.entities

object MoneyAmount {
  def apply(amount: Double, currency: String): MoneyAmount = new MoneyAmount(amount)
}

class MoneyAmount(val amount: Double) extends AnyVal {
  def +(value: Double): MoneyAmount = new MoneyAmount(amount + value)

  def -(value: Double): MoneyAmount = new MoneyAmount(amount - value)

  def >(value: Double): Boolean = amount > value

  override def toString: String = "$" + f"$amount%.2f"
}
