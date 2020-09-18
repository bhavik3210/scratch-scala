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

  def saveProduct(product: Product): Unit = product match {
      case d: Deposit => depositsProducts += (d.id -> d)
      case l: Lending => lendingProducts += (l.id -> l)
    }

  def getDepositProducts(id: UUID): Option[Deposit] = depositsProducts.get(id)
  def getLendingProducts(id: UUID): Option[Lending] = lendingProducts.get(id)

  def numberOfDepositsProducts: Int = depositsProducts.size
  def numberOfLendingProducts: Int = lendingProducts.size
}

trait AccountsDb {
  private var depositAccounts: Map[UUID, DepositAccount] = Map.empty
  private var lendingAccounts: Map[UUID, LendingAccount] = Map.empty

  def saveAccounts(account: Account) = account match {
    case d: DepositAccount => depositAccounts += (d.id -> d)
    case l: LendingAccount => lendingAccounts += (l.id -> l)
  }

  def getDepositAccounts(id: UUID): Option[DepositAccount] = depositAccounts.get(id)
  def getLendingAccounts(id: UUID): Option[LendingAccount] = lendingAccounts.get(id)

  def numberOfDepositAccounts: Int = depositAccounts.size
  def numberOfLendingAccounts: Int = lendingAccounts.size
}
