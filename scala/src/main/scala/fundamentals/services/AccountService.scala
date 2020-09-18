package fundamentals.services

import java.util.UUID

import fundamentals.entities.{Currency, DepositAccount, LendingAccount, MoneyAmount}

trait AccountService extends AccountsDb with CustomerService with ProductService {

  def openDepositAccount(customerId: UUID, productId: UUID, amount: MoneyAmount): UUID = {
    require(getCustomer(customerId).nonEmpty, s"no customer found with ID: $customerId")
    require(getDepositProducts(productId).nonEmpty, s"No product found with ID: $productId")

    val account = new DepositAccount(getCustomer(customerId).get, getDepositProducts(productId).get, amount)
    saveAccounts(account)
    account.id
  }

  def openLendingAccount(customerId: UUID, productId: UUID, balance: MoneyAmount = MoneyAmount(0)): UUID = {
    require(getCustomer(customerId).nonEmpty, s"no customer found with ID: $customerId")
    require(getLendingProducts(productId).nonEmpty, s"No product found with ID: $productId")

    val account = new LendingAccount(getCustomer(customerId).get, getLendingProducts(productId).get, balance)
    saveAccounts(account)
    account.id
  }

  def deposit(accountId: UUID, money: MoneyAmount): Unit = {
    require(getDepositAccounts(accountId).nonEmpty, s"no accountId found with ID: $accountId")
    getDepositAccounts(accountId).get.deposit(money)
  }

  def withdraw(accountId: UUID, money: MoneyAmount): Unit = {
    require(getDepositAccounts(accountId).nonEmpty, s"no accountId found with ID: $accountId")
    getDepositAccounts(accountId).get withdraw money
  }

  def requestCurrency(accountId: UUID, currency: Currency): Unit = {
    withdraw(accountId, currency.costInMoney)
    println(s"The ${currency.amount} ${currency.code} will be posted to your nearest branch in 2 days.")
  }

  def useCreditCard(accountId: UUID, money: MoneyAmount): Unit = {
    require(getLendingAccounts(accountId).nonEmpty, s"no accountId found with ID: $accountId")
    getLendingAccounts(accountId).get withdraw money
  }

  def payCreditCard(accountId: UUID, money: MoneyAmount): Unit = {
    require(getLendingAccounts(accountId).nonEmpty, s"no accountId found with ID: $accountId")
    getLendingAccounts(accountId).get payBill money
  }
}
