package fundamentals.entities

abstract class Account {
  val customer: Customer
  val product: Product

  def getBalance: MoneyAmount
  override def toString: String = s"$customer with $product has remaining balance of $getBalance"
}

class DepositAccount(val customer: Customer, val product: Product, private var balance: MoneyAmount) extends Account {

  def deposit(amount: Double): Unit = {
    require(amount > 0.0, "amount should be higher than 0")
    println(s"Depositing $amount to $customer account")
    balance += amount
  }

  def withdraw(amount: Double): Unit = {
    require(amount > 0 && amount < balance.amount, "insufficient funds")
    println(s"Withdrawing $amount to $customer account")
    balance -= amount
  }

  override def getBalance: MoneyAmount = balance

  override def toString: String = s"$customer with $product has remaining balance of $balance"
}

class LendingAccount(val customer: Customer, val product: Product, private var balance: MoneyAmount) extends Account {

  def payBill(amount: Double): Unit = {
    require(amount > 0, "payment must be higher than 0")
    println(s"Paying bill of $amount against $customer account")
    balance += amount
  }

  def withdraw(amount: Double): Unit = {
    require(amount > 0, "withdraw must be higher than 0")
    println(s"debiting $amount from $customer account")
    balance -= amount
  }

  override def getBalance: MoneyAmount = balance

  override def toString: String = s"$customer with $product has remaining balance of $balance"
}