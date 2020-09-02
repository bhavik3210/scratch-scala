package fundamentals.entities

object SupportedCurrency extends Enumeration {
  val USD: SupportedCurrency.Value = Value("$")
}

object MoneyAmount {
  def apply(amount: Double, currency: SupportedCurrency.Value = SupportedCurrency.USD): MoneyAmount = new MoneyAmount(amount)
}

class MoneyAmount(val amount: Double) extends AnyVal {
  def +(money: MoneyAmount): MoneyAmount = new MoneyAmount(amount + money.amount)

  def -(money: MoneyAmount): MoneyAmount = new MoneyAmount(amount - money.amount)

  def >(money: MoneyAmount): Boolean = amount > money.amount

  override def toString: String = f"$amount%.2f"
}
