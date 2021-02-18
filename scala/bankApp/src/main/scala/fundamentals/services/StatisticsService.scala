package fundamentals.services

import fundamentals.entities.{Account, AccountCategory, DepositA, LendingA, MoneyAmount}

trait StatisticsService {
  def getTotalMoneyDeposited(accounts: Seq[Account]): MoneyAmount = {
    accounts.foldLeft(MoneyAmount.Zero)((total, account) => if (account.category == DepositA) total + account.getBalance else total)
  }

  def getTotalMoneyBorrowedByCustomers(accounts: Seq[Account]): MoneyAmount = {
    accounts map { a => if (a.category == LendingA) a.getBalance else MoneyAmount.Zero } reduce (_ + _)
  }

  def getNumTransactionsByAccount(accounts: Seq[Account]): Map[String, Int] = {
    val tuples: Seq[(AccountCategory, Int)] = accounts.map { a => a.category -> a.transactions.length}
    val categoryToTuples: Map[AccountCategory, Seq[(AccountCategory, Int)]] = tuples.groupBy(_._1)
    categoryToTuples map {
      case (accountCategory, rest) => accountCategory.toString -> rest.map(_._2).sum
    }
  }
}
