package uk.filippov.addressbook

import java.time.LocalDate

import kantan.csv._
import kantan.csv.ops._
import org.scalatest.{FlatSpec, Matchers}
import uk.filippov.addressbook.AddressBook.Person

import scala.io.Source.fromInputStream

class AddressBookTest extends FlatSpec with Matchers {
  val SampleAddressBook = List(
    Person("Bill McKnight", "Male", date(1977, 3, 16)),
    Person("Paul Robinson", "Male", date(1985, 1, 15)),
    Person("Gemma Lane", "Female", date(1991, 11, 20)),
    Person("Sarah Stone", "Female", date(1980, 9, 20)),
    Person("Wes Jackson", "Male", date(1974, 8, 14))
  )

  val SampleNontrivialAddressBook = List(
    Person("Bill McKnight", "Male", date(1977, 3, 16)),
    Person("Robinson, Paul", "Male", date(1985, 1, 15)),
    Person("Gemma Lane", "Female", date(1991, 11, 20)),
    Person("Sarah Stone", "Female", date(2014, 9, 20)),
    Person("Wes \"Jack\" Jackson", "Male", date(1974, 8, 14))
  )

  "The address book" should "be loaded from file" in {
    assert(AddressBook.loadBook(fromInputStream(getClass.getResourceAsStream("/AddressBook"))) ==
      SampleAddressBook)
  }

  "The address book" should "be loaded from a quoted file with escapes" in {
    val book = AddressBook.loadBook(fromInputStream(getClass.getResourceAsStream("/NontrivialAddressBook")))
    assert(book == SampleNontrivialAddressBook)
  }

  "The count of males" should "be correct" in {
    assert(AddressBook.countMales(SampleAddressBook) == 3)
  }

  "The oldest person" should "be Wes" in {
    assert(AddressBook.oldest(SampleAddressBook) == Person("Wes Jackson", "Male", date(1974, 8, 14)))
  }

  "The age difference between Paul and Bill" should "be 2862" in {
    val Some(bill) = SampleAddressBook.find(_.name.startsWith("Bill "))
    val Some(paul) = SampleAddressBook.find(_.name.startsWith("Paul "))

    assert(AddressBook.ageDifferenceInDays(bill, paul) == 2862)
  }

  "The date parser" should "work" in {
    implicit val customDateDecoder: CellDecoder[LocalDate] = AddressBook.customDateDecoder
    val input = "1,10/12/78\n2,09/01/15"
    val res = input.unsafeReadCsv[List, (Int, LocalDate)](rfc)
    assert(res == List((1, date(1978, 12, 10)), (2, date(2015, 1, 9))))
  }

  "The person parser" should "work" in {
    implicit val customDateDecoder: CellDecoder[LocalDate] = AddressBook.customDateDecoder
    implicit val personDecoder: RowDecoder[Person] = AddressBook.personDecoder
    val input = "Bill McKnight, Male, 16/03/77\nPaul Robinson, Male, 15/01/85"
    val res = input.unsafeReadCsv[List, Person](rfc)
    assert(res == List(
      Person("Bill McKnight", "Male", date(1977, 3, 16)),
      Person("Paul Robinson", "Male", date(1985, 1, 15))))
  }

  def date(year: Int, month: Int, dayOfMonth: Int): LocalDate =
    LocalDate.of(year, month, dayOfMonth)
}
