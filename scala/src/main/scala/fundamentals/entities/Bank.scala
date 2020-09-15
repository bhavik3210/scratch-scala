package fundamentals.entities

import fundamentals.services.{AccountService, CustomerService, ProductService, StatisticsService}


class Bank(name: String, city: String, country: String, email: String) extends CustomerService
  with ProductService
  with AccountService
  with StatisticsService {

  println(s"\n$name Created\n")

  override def toString: String = s"[$name]" +
    s" - ${numberOfCustomers} customers" +
    s" - ${numberOfDepositsProducts} deposit products" +
    s" - ${numberOfDepositAccounts} deposit accounts" +
    s" - ${numberOfLendingProducts} lending products" +
    s" - ${numberOfLendingAccounts} lending accounts"
}
