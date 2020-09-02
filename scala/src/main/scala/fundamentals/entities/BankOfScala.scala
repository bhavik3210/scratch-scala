package fundamentals.entities

import java.time.LocalDate

import fundamentals.entities.PRODUCT_TYPES._

import scala.util.Random

object BankOfScala {
  def bankOfScala(): Unit = {
    println("================================================================================================================")
    println("Instantiating Bank")
    println("================================================================================================================")
    val bank = new Bank("Bank of Scala", "Auckland", "New Zealand", "bank@scala.com")
    val customerIds = getCustomers map (customer => bank.createNewCustomer(customer._1, customer._2, customer._3, customer._4))
    val depositProductIds = getDepositProducts map (product => bank.addNewDepositProduct(product._1, product._2, product._3))
    val lendingProductIds = getLendingProducts map (product => bank.addNewLendingProduct(product._1, product._2, product._3, product._4))
    println("================================================================================================================")
    println(s"Bank: $bank \n")
    println(s"CustomerIds: $customerIds \n")
    println(s"Deposit Products Ids: $depositProductIds \n")
    println(s"Lending Products Ids: $lendingProductIds \n")

    println("================================================================================================================")
    println("================================================ Opening Account ===============================================")
    println("================================================================================================================")
    val depositAccounts = for {
      cId <- customerIds
      pId <- depositProductIds
    } yield bank.openDepositAccount(cId, pId, _: MoneyAmount)

    for {
      account <- depositAccounts
    } yield println(s"$account")

    println("================================================================================================================")
    println("================================================= Deposit Money ================================================")
    println("================================================================================================================")
    val random = new Random()
    val depositAccountIds = depositAccounts.map(account => account(MoneyAmount(10000 + random.nextInt(10000))))
    for {
      account <- depositAccountIds
    } yield println(s"$account")

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
