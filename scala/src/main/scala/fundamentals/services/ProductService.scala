package fundamentals.services

import java.util.UUID

import fundamentals.entities.{BasicChecking, CreditCard, MoneyAmount, PRODUCT_TYPES, RewardsSaving, StudentChecking, SupportedCurrency}

trait ProductService extends ProductsDb {
  def addNewDepositProduct(productName: PRODUCT_TYPES.Value, minimumBalance: Int, ratePerYear: Double, transactionsAllowedPerMonth: Int = 4): UUID = {
    val product = productName match {
      case PRODUCT_TYPES.BASIC_CHECKING => new BasicChecking(MoneyAmount.apply(minimumBalance, SupportedCurrency.USD), ratePerYear)
      case PRODUCT_TYPES.STUDENT_CHECKING => new StudentChecking(MoneyAmount.apply(minimumBalance, SupportedCurrency.USD), ratePerYear)
      case PRODUCT_TYPES.REWARDS_SAVINGS => new RewardsSaving(MoneyAmount.apply(minimumBalance, SupportedCurrency.USD), ratePerYear, transactionsAllowedPerMonth)
    }
    saveProduct(product)
    product.id
  }

  def addNewLendingProduct(productName: PRODUCT_TYPES.Value, annualFees: Double, apr: Double, rewardsPercentage: Double): UUID = {
    val product = productName match {
      case PRODUCT_TYPES.CREDIT_CARD => new CreditCard(annualFees, apr, rewardsPercentage)
    }
    saveProduct(product)
    product.id
  }
}
