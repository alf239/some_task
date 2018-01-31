package uk.filippov.addressbook

import org.scalatest.{FlatSpec, Matchers}
import uk.filippov.addressbook.AddressBook.Person

class MaleCountTest extends FlatSpec with Matchers {
  "The count of males" should "be correct" in {
    assert(AddressBook.countMales(List(
      Person("Bill McKnight", "Male", "16/03/77"),
      Person("Paul Robinson", "Male", "15/01/85"),
      Person("Gemma Lane", "Female", "20/11/91"),
      Person("Sarah Stone", "Female", "20/09/80"),
      Person("Wes Jackson", "Male", "14/08/74")
    )) == 3)
  }
}
