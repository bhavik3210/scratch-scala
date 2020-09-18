package fundamentals.entities

import java.util.UUID

import fundamentals.entities.PRODUCT_TYPES._

import scala.util.Random

object BankOfScala {
  val random = new Random()

  def bankOfScala(): Unit = {

    printSeparators(heading = "Instantiating Bank")

    val bank = new Bank("Bank of Scala", "Auckland", "New Zealand", "bank@scala.com")
    val customerIds = getCustomers map { customer => bank.createNewCustomer(customer._1, customer._2, customer._3, customer._4) }
    val depositProductIds = getDepositProducts map { product => bank.addNewDepositProduct(product._1, product._2, product._3) }
    val lendingProductIds = getLendingProducts map { product => bank.addNewLendingProduct(product._1, product._2, product._3, product._4) }

    printBankBusinessInfo(bank, customerIds, depositProductIds, lendingProductIds)

    def openAccounts(customerId: UUID, product: Product) = product.category match {
      case DepositP => bank.openDepositAccount(customerId, product.id, _: MoneyAmount)
      case LendingP => bank.openLendingAccount(customerId, product.id, _: MoneyAmount)
    }

    printSeparators(heading = "Opening Account")
    val depositAccounts = for (cId <- customerIds; pId <- depositProductIds) yield openAccounts(cId, bank.getDepositProducts(pId).get)
    for {account <- depositAccounts} yield println(s"$account")

    printSeparators(heading = "Deposit Money")
    val random = new Random()
    val depositAccountIds = depositAccounts.map { account => account(MoneyAmount(10000 + random.nextInt(10000))) }
    for {account <- depositAccountIds} yield println(s"$account")


    printSeparators(heading = "Opening CreditCard")
    val lendingAccounts = for {cId <- customerIds; pId <- lendingProductIds} yield openAccounts(cId, bank.getLendingProducts(pId).get)
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

    printSeparators(heading = "Requesting Currency")
    bank.requestCurrency(depositAccountIds.head, "120 USD")
    bank.requestCurrency(depositAccountIds.tail.head, "120 CAD")

    printSeparators(heading = "Statistics")
    val dAccounts = depositAccountIds flatMap {
      bank.getDepositAccounts
    }
    println(s"Total money deposited to Bank: ${bank.getTotalMoneyDeposited(dAccounts)}")

    val lAccounts = lendingAccountIds flatMap {
      bank.getLendingAccounts
    }
    println(s"Total money borrowed by Customers: ${bank.getTotalMoneyBorrowedByCustomers(lAccounts)}")

    val allAccounts = dAccounts ++ lAccounts
    println(s"Number of Transactions By Account: ${bank.getNumTransactionsByAccount(allAccounts)}")
  }

  def printSeparators(repeater: Int = 100, heading: String = ""): Unit = {
    println()
    println("=" * repeater)
    if (!heading.isEmpty) {
      val spacer = repeater / 2 - (heading.length / 2)
      println(s"${" " * spacer} $heading ${" " * spacer}")
      println("=" * repeater)
    }
  }

  def printBankBusinessInfo(bank: Bank, customerIds: Seq[UUID], depositProductIds: Seq[UUID], lendingProductIds: Seq[UUID]): Unit = {
    println()
    println("*" * 100)
    println(s"Bank: $bank \n")
    println(s"CustomerIds: $customerIds \n")
    println(s"Deposit Products Ids: $depositProductIds \n")
    println(s"Lending Products Ids: $lendingProductIds \n")
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
