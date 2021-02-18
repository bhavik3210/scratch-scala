package fundamentals.entities

import java.util.UUID

sealed trait ProductCategory
case object DepositP extends ProductCategory
case object LendingP extends ProductCategory

object PRODUCT_TYPES extends Enumeration {
  val BASIC_CHECKING: PRODUCT_TYPES.Value = Value("Basic Checking")
  val STUDENT_CHECKING: PRODUCT_TYPES.Value = Value("Student Checking")
  val REWARDS_SAVINGS: PRODUCT_TYPES.Value = Value("Rewards Checking")
  val CREDIT_CARD: PRODUCT_TYPES.Value = Value("Credit Card")
}

abstract class Product {
  val id: UUID = UUID.randomUUID()
  val name: String
  val category: ProductCategory

  override def toString: String = s"product=$name"
}

abstract class Deposit extends Product {
  val rate: Double
  val minimumBalanceRequired: MoneyAmount
  val category: ProductCategory = DepositP
}

abstract class Checking extends Deposit

abstract class Saving extends Deposit {
  val transactionsAllowedPerMonth: Int
}

class BasicChecking(balance: MoneyAmount, ratePerYear: Double) extends Checking {
  println("Created Basic Checking Account")
  override val rate: Double = rate
  override val minimumBalanceRequired: MoneyAmount = balance
  override val name: String = "Basic Checking Account"
}

class StudentChecking(balance: MoneyAmount, ratePerYear: Double) extends Checking {
  println("Created Student Checking Account")
  override val rate: Double = ratePerYear
  override val minimumBalanceRequired: MoneyAmount = balance
  override val name: String = "Student Checking Account"
}

class RewardsSaving(balance: MoneyAmount, ratePerYear: Double, transactionLimit: Int) extends Saving {
  println("Created Rewards Savings Account")
  override val transactionsAllowedPerMonth: Int = transactionLimit
  override val rate: Double = ratePerYear
  override val minimumBalanceRequired: MoneyAmount = balance
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
  override val category: ProductCategory = LendingP
}