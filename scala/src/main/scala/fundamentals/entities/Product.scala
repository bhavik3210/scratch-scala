package fundamentals.entities

import java.util.UUID

abstract class Product {
  val id: UUID = UUID.randomUUID()
  val name: String
  override def toString: String = s"product=$name"
}

abstract class Deposits extends Product {
  val rate: Double
  val minimumBalanceRequired: Int
}

abstract class Checking extends Deposits

abstract class Saving extends Deposits {
  val transactionsAllowedPerMonth: Int
}

class BasicChecking(balance: Int, ratePerYear: Double) extends Checking {
  println("Created Basic Checking Account")
  override val rate: Double = rate
  override val minimumBalanceRequired: Int = balance
  override val name: String = "Basic Checking Account"
}

class StudentChecking(balance: Int, ratePerYear: Double) extends Checking {
  println("Created Student Checking Account")
  override val rate: Double = ratePerYear
  override val minimumBalanceRequired: Int = balance
  override val name: String = "Student Checking Account"
}

class RewardsSaving(balance: Int, ratePerYear: Double, transactionLimit: Int) extends Saving {
  println("Created Rewards Savings Account")
  override val transactionsAllowedPerMonth: Int = transactionLimit
  override val rate: Double = ratePerYear
  override val minimumBalanceRequired: Int = balance
  override val name: String = "Rewards Savings Account"
}

abstract class Lending extends Product {
  val annualFee: Double
  val annualPercentageRate: Double
  val rewardsPercentage: Double
}

class CreditCard(fee: Double, rate: Double, pct: Double) extends Lending {
  println("Created Credit Card Product")
  override val annualFee: Double = fee
  override val annualPercentageRate: Double = rate
  override val rewardsPercentage: Double = pct
  override val name: String = "Credit Card"
}