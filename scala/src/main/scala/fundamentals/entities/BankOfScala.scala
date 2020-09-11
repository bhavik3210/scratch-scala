package fundamentals.entities

import java.time.LocalDate
import java.util.UUID

import fundamentals.entities.PRODUCT_TYPES._

import scala.util.Random

object BankOfScala {
  def bankOfScala(): Unit = {
    println("================================================================================================================")
    println("Instantiating Bank")
    println("================================================================================================================")
    val bank = new Bank("Bank of Scala", "Auckland", "New Zealand", "bank@scala.com")
    val customerIds = getCustomers map { customer => bank.createNewCustomer(customer._1, customer._2, customer._3, customer._4) }
    val depositProductIds = getDepositProducts map { product => bank.addNewDepositProduct(product._1, product._2, product._3) }
    val lendingProductIds = getLendingProducts map { product => bank.addNewLendingProduct(product._1, product._2, product._3, product._4) }
    println("================================================================================================================")
    println(s"Bank: $bank \n")
    println(s"CustomerIds: $customerIds \n")
    println(s"Deposit Products Ids: $depositProductIds \n")
    println(s"Lending Products Ids: $lendingProductIds \n")

    def openAccounts(customerId: UUID, productId: UUID, productType: String) = productType match {
      case "Deposit" => bank.openDepositAccount(customerId, productId, _: MoneyAmount)
      case "Lending" => bank.openLendingAccount(customerId, productId, _: MoneyAmount)
    }

    println("================================================================================================================")
    println("================================================ Opening Account ===============================================")
    println("================================================================================================================")
    val depositAccounts = for (cId <- customerIds; pId <- depositProductIds) yield openAccounts(cId, pId, "Deposit")
    for {account <- depositAccounts} yield println(s"$account")

    println("================================================================================================================")
    println("================================================= Deposit Money ================================================")
    println("================================================================================================================")
    val random = new Random()
    val depositAccountIds = depositAccounts.map { account => account(MoneyAmount(10000 + random.nextInt(10000))) }
    for {
      account <- depositAccountIds
    } yield println(s"$account")


    println("================================================================================================================")
    println("======================================+=========== Opening CreditCard ==============+===========================")
    println("================================================================================================================")
    val lendingAccounts = for {cId <- customerIds; pId <- lendingProductIds} yield openAccounts(cId, pId, "Lending")
    val lendingAccountIds = lendingAccounts map { account => account(MoneyAmount(random.nextInt(500))) }
    for {account <- lendingAccountIds} yield println(s"$account.id")

    val randomAmount = new Random(100)
    depositAccountIds.foreach {
      bank deposit(_, MoneyAmount(1 + randomAmount.nextInt(100)))
      bank withdraw(_, MoneyAmount(1 + randomAmount.nextInt(50)))
    }

    lendingAccountIds.foreach {
      bank useCreditCard(_, MoneyAmount(1 + randomAmount.nextInt(500)))
      bank payCreditCard(_, MoneyAmount(1 + randomAmount.nextInt(100)))
    }
  }

  def getCustomers: Seq[(String, String, String, String)] = {
    Seq(
      ("Bob", "Martin", "bob@martin.com", "1973/11/23"),
      ("Mark", "Martin", "mark@martin.com", "1990/12/3"),
      ("Josh", "Martin", "josh@martin.com", "2001/4/12")
    )
  }

  def getDepositProducts: Seq[(PRODUCT_TYPES.Value, Int, Double)] = {
    Seq(
      (BASIC_CHECKING, 1000, 0.025),
      (STUDENT_CHECKING, 0, 0.010),
      (REWARDS_SAVINGS, 10000, 0.10)
    )
  }

  def getLendingProducts: Seq[(PRODUCT_TYPES.Value, Double, Double, Double)] = {
    Seq(
      (CREDIT_CARD, 99.00, 14.23, 20.00)
    )
  }
}
