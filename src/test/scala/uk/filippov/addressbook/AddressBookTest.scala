package uk.filippov.addressbook

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}
import uk.filippov.addressbook.AddressBook.Person

import scala.io.Source.fromInputStream

class AddressBookTest extends FlatSpec with Matchers {
  val SampleAddressBook = List(
    Person("Bill McKnight", "Male", "16/03/77"),
    Person("Paul Robinson", "Male", "15/01/85"),
    Person("Gemma Lane", "Female", "20/11/91"),
    Person("Sarah Stone", "Female", "20/09/80"),
    Person("Wes Jackson", "Male", "14/08/74")
  )

  val SampleNontrivialAddressBook = List(
    Person("Bill McKnight", "Male", "16/03/77"),
    Person("Robinson, Paul", "Male", "15/01/85"),
    Person("Gemma Lane", "Female", "20/11/1991"),
    Person("Sarah Stone", "Female", "20/09/2014"),
    Person("Wes \"Jack\" Jackson", "Male", "14/08/74")
  )

  "The address book" should "be loaded from file" in {
    assert(AddressBook.loadBook(fromInputStream(getClass.getResourceAsStream("/AddressBook"))) ==
      SampleAddressBook)
  }

  "The address book" should "be loaded from a quoted file with escapes" in {
    val book = AddressBook.loadBook(fromInputStream(getClass.getResourceAsStream("/NontrivialAddressBook")))
    assert(book == SampleNontrivialAddressBook)
    assert(AddressBook.dateOfBirth(book(3)) == date(2014, 9, 20))
  }

  "The count of males" should "be correct" in {
    assert(AddressBook.countMales(SampleAddressBook) == 3)
  }

  "The oldest person" should "be Wes" in {
    assert(AddressBook.oldest(SampleAddressBook) == Person("Wes Jackson", "Male", "14/08/74"))
  }

  "The age difference between Paul and Bill" should "be 2862" in {
    val Some(bill) = SampleAddressBook.find(_.name.startsWith("Bill "))
    val Some(paul) = SampleAddressBook.find(_.name.startsWith("Paul "))

    assert(AddressBook.ageDifference(bill, paul) == 2862)
  }

  "The dates" should "be reasonable" in {
    val dates = SampleAddressBook.map(AddressBook.dateOfBirth)
    assert(dates == List(
      date(1977, 3, 16), date(1985, 1, 15), date(1991, 11, 20), date(1980, 9, 20), date(1974, 8, 14)
    ))
  }

  def date(year: Int, month: Int, dayOfMonth: Int): LocalDate =
    LocalDate.of(year, month, dayOfMonth)
}
