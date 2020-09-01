package fundamentals.entities

object MoneyAmount {
  def apply(amount: Double, currency: String): MoneyAmount = new MoneyAmount(amount)
}

class MoneyAmount(val amount: Double) extends AnyVal {
  def +(money: MoneyAmount): MoneyAmount = new MoneyAmount(amount + money.amount)

  def -(money: MoneyAmount): MoneyAmount = new MoneyAmount(amount - money.amount)

  def >(money: MoneyAmount): Boolean = amount > money.amount

  override def toString: String =  f"$amount%.2f"
}
