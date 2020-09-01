package fundamentals.entities

import java.time.LocalDate

object BankOfScala {
  def bankOfScala(): Unit = {
    println("Instantiating Bank")

    val basicChecking = new BasicChecking(1000, 0.025)
    val studentChecking = new StudentChecking(0, 0.010)
    val rewardsSaving = new RewardsSaving(10000, .10, 1)
    val creditCard = new CreditCard(99.00, 14.23, 20.00)
    val products = Set(basicChecking, studentChecking, rewardsSaving, creditCard)

    val bobMartin = new Customer("Bob", "Martin", "bob@martin.com", LocalDate.of(1983, 8, 22))
    val bobCheckingAccount = new DepositAccount(bobMartin, basicChecking, MoneyAmount(1000, "USD"))
    val bobSavingsAccount = new DepositAccount(bobMartin, rewardsSaving, MoneyAmount(20000, "USD"))
    val bobCreditAccount = new LendingAccount(bobMartin, creditCard, MoneyAmount(4500, "USD"))
    val accounts = Set(bobCheckingAccount, bobSavingsAccount, bobCreditAccount)

    val bank = new Bank("Bank of Scala", "Auckland", "New Zealand", "bank@scala.com", products, Set(bobMartin), accounts)

    println(bobCheckingAccount)

    bobCheckingAccount.deposit(100)
    println(bobCheckingAccount)

    bobCheckingAccount.withdraw(200)
    println(bobCheckingAccount)
  }
}
