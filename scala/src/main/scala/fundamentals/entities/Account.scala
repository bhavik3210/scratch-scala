package fundamentals.entities

import java.util.UUID

abstract class Account {
  val id: UUID = UUID.randomUUID()
  val customer: Customer
  val product: Product

  def getBalance: MoneyAmount
  override def toString: String = s"$customer with $product has remaining balance of $getBalance"
}

class DepositAccount(val customer: Customer, val product: Product, private var balance: MoneyAmount) extends Account {

  def deposit(money: MoneyAmount): Unit = {
    require(money.amount > 0.0, "amount should be higher than 0")
    println(s"Depositing $money to $customer account")
    balance += money
  }

  def withdraw(money: MoneyAmount): Unit = {
    require(money.amount > 0 && money.amount < balance.amount, "insufficient funds")
    println(s"Withdrawing $money to $customer account")
    balance -= money
  }

  override def getBalance: MoneyAmount = balance

  override def toString: String = s"$customer with $product has remaining balance of $balance"
}

class LendingAccount(val customer: Customer, val product: Product, private var balance: MoneyAmount) extends Account {

  def payBill(money: MoneyAmount): Unit = {
    require(money.amount > 0, "payment must be higher than 0")
    println(s"Paying bill of $money against $customer account")
    balance += money
  }

  def withdraw(money: MoneyAmount): Unit = {
    require(money.amount > 0, "withdraw must be higher than 0")
    println(s"debiting $money.am from $customer account")
    balance -= money
  }

  override def getBalance: MoneyAmount = balance

  override def toString: String = s"$customer with $product has remaining balance of $balance"
}