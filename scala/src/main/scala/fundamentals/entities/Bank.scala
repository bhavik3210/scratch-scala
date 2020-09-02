package fundamentals.entities

import java.time.LocalDate
import java.util.UUID


class Bank(name: String, city: String, country: String, email: String) {

  private var depositProducts: Map[UUID, Deposits] = Map.empty
  private var depositAccounts: Map[UUID, DepositAccount] = Map.empty
  private var lendingProducts: Map[UUID, Lending] = Map.empty
  private var lendingAccounts: Map[UUID, LendingAccount] = Map.empty
  private var customers: Map[UUID, Customer] = Map.empty

  println(s"\n $name Created on ${LocalDate.now()} \n")

  /**
   *
   * @param first       :first name of customer
   * @param last        :last name of customer
   * @param email       :email of customer
   * @param dateOfBirth :birth date of customer
   * @return
   */
  def createNewCustomer(first: String, last: String, email: String, dateOfBirth: String): UUID = {
    def getEmail: Email = {
      val Array(value, domain) = email.split("@") //like javascript destructuring
      Email(value, domain)
    }

    def getDateOfBirth: LocalDate = {
      val Array(year, month, day) = dateOfBirth.split("/")
      LocalDate.of(year.toInt, month.toInt, day.toInt)
    }

    val customer = new Customer(f = first, l = last, e = getEmail, dob = getDateOfBirth)
    customers += (customer.id -> customer)
    customer.id
  }

  /**
   *
   * @param productName                 :name of product
   * @param minimumBalance              :minimum balance required for a desired product
   * @param ratePerYear                 :annual rate of charge
   * @param transactionsAllowedPerMonth :maximum transactions allowed per month
   * @return
   */
  def addNewDepositProduct(productName: PRODUCT_TYPES.Value, minimumBalance: Int, ratePerYear: Double, transactionsAllowedPerMonth: Int = 5): UUID = {
    val product = productName match {
      case PRODUCT_TYPES.BASIC_CHECKING => new BasicChecking(MoneyAmount.apply(minimumBalance, SupportedCurrency.USD), ratePerYear)
      case PRODUCT_TYPES.STUDENT_CHECKING => new StudentChecking(MoneyAmount.apply(minimumBalance, SupportedCurrency.USD), ratePerYear)
      case PRODUCT_TYPES.REWARDS_SAVINGS => new RewardsSaving(MoneyAmount.apply(minimumBalance, SupportedCurrency.USD), ratePerYear, transactionsAllowedPerMonth)
    }

    depositProducts += (product.id -> product)
    product.id
  }

  def addNewLendingProduct(productName: PRODUCT_TYPES.Value, annualFees: Double, apr: Double, rewardsPercentage: Double): UUID = {
    val product = new CreditCard(annualFees, apr, rewardsPercentage)
    lendingProducts += (product.id -> product)
    product.id
  }

  def openDepositAccount(customerId: UUID, productId: UUID, amount: MoneyAmount): UUID = {
    require(customers.contains(customerId), s"no customer found with ID: $customerId")
    require(depositProducts.contains(productId), s"No product found with ID: $productId")

    val account = new DepositAccount(customers(customerId), depositProducts(productId), amount)
    depositAccounts += (account.id -> account)
    account.id
  }

  def openLendingAccount(customerId: UUID, productId: UUID, balance: MoneyAmount = MoneyAmount(0)): UUID = {
    require(customers.contains(customerId), s"no customer found with ID: $customerId")
    require(lendingProducts.contains(productId), s"No product found with ID: $productId")

    val account = new LendingAccount(customers(customerId), lendingProducts(productId), balance)
    lendingAccounts += (account.id -> account)
    account.id
  }

  def deposit(accountId: UUID, money: MoneyAmount): Unit = {
    require(depositAccounts.contains(accountId), s"no accountId found with ID: $accountId")
    depositAccounts(accountId).deposit(money)
  }

  def withdraw(accountId: UUID, money: MoneyAmount): Unit = {
    require(depositAccounts.contains(accountId), s"no accountId found with ID: $accountId")
    depositAccounts(accountId) withdraw money
  }

  def transactCreditCard(accountId: UUID, money: MoneyAmount): Unit = {
    require(lendingAccounts.contains(accountId), s"no accountId found with ID: $accountId")
    lendingAccounts(accountId) withdraw money
  }

  def payCreditCard(accountId: UUID, money: MoneyAmount): Unit = {
    require(lendingAccounts.contains(accountId), s"no accountId found with ID: $accountId")
    lendingAccounts(accountId) payBill money
  }

  override def toString: String = s"[$name]" +
    s" - ${customers.size} customers" +
    s" - ${depositProducts.size} deposit products" +
    s" - ${depositAccounts.size} deposit accounts" +
    s" - ${lendingProducts.size} lending products" +
    s" - ${lendingAccounts.size} lending accounts"
}
