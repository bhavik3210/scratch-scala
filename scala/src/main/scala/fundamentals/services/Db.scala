package fundamentals.services

import java.util.UUID

import fundamentals.entities.{Account, Customer, Deposit, DepositAccount, Lending, LendingAccount, Product}

trait CustomerDb {
  private var customers: Map[UUID, Customer] = Map.empty
  def saveCustomer(customer: Customer): Unit = customers += (customer.id -> customer)
  def getCustomer(customerId: UUID): Option[Customer] = customers.get(customerId)
  def numberOfCustomers: Int = customers.size
}

trait ProductsDb {
  private var depositsProducts: Map[UUID, Deposit] = Map.empty
  private var lendingProducts: Map[UUID, Lending] = Map.empty

  def saveAccounts(product: Product): Unit = product match {
      case d: Deposit => depositsProducts += (d.id -> d)
      case l: Lending => lendingProducts += (l.id -> l)
    }

  def getDepositAccounts(id: UUID): Option[Deposit] = depositsProducts.get(id)
  def getLendingAccounts(id: UUID): Option[Lending] = lendingProducts.get(id)

  def numberOfDepositsAccounts: Int = depositsProducts.size
  def numberOfLendingAccounts: Int = lendingProducts.size
}

trait AccountsDb {
  private var depositAccounts: Map[UUID, DepositAccount] = Map.empty
  private var lendingAccounts: Map[UUID, LendingAccount] = Map.empty

  def saveDepositAccounts(account: Account) = account match {
    case d: DepositAccount => depositAccounts += (d.id -> d)
    case l: LendingAccount => lendingAccounts += (l.id -> l)
  }

  def numberOfDepositAccounts: Int = depositAccounts.size
  def numberOfLendingAccounts: Int = lendingAccounts.size
}
