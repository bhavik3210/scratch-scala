package fundamentals.entities

import java.time.LocalDate
import java.util.UUID

import fundamentals.services.{AccountService, CustomerService, ProductService}


class Bank(name: String, city: String, country: String, email: String) extends CustomerService
  with ProductService
  with AccountService {

  println(s"\n $name Created on ${LocalDate.now()} \n")

  override def toString: String = s"[$name]" +
    s" - ${numberOfCustomers} customers" +
    s" - ${numberOfDepositsProducts} deposit products" +
    s" - ${numberOfDepositAccounts} deposit accounts" +
    s" - ${numberOfLendingProducts} lending products" +
    s" - ${numberOfLendingAccounts} lending accounts"
}
