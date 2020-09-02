package fundamentals.entities

import java.time.LocalDate
import java.util.UUID

class Customer(f: String, l: String, e: Email, dob: LocalDate) {
  val id: UUID = UUID.randomUUID()
  val first: String = f
  val last: String = l
  val email: Email = e
  val dateOfBirth: LocalDate = dob

  override def toString: String = s"$first,$last"
}