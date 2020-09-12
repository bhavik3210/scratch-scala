package fundamentals.entities

object SupportedCurrency extends Enumeration {
  val USD: SupportedCurrency.Value = Value("$")
}

object MoneyAmount {
  val Zero = new MoneyAmount(0)
  def apply(amount: Double, currency: SupportedCurrency.Value = SupportedCurrency.USD): MoneyAmount = new MoneyAmount(amount)
}

class MoneyAmount(val amount: Double) extends AnyVal with Ordered[MoneyAmount] {
  override def compare(that: MoneyAmount): Int = (amount - that.amount) toInt

  def +(money: MoneyAmount): MoneyAmount = new MoneyAmount(amount + money.amount)
  def -(money: MoneyAmount): MoneyAmount = new MoneyAmount(amount - money.amount)
  override def toString: String = f"$amount%.2f"
}
