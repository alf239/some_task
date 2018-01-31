package uk.filippov.addressbook

import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit.DAYS

object AddressBook {

  case class Person(name: String, gender: String, birthday: String)

  def ageDifference(a: Person, b: Person) =
    DAYS.between(dateOfBirth(a), dateOfBirth(b))

  def oldest(people: List[Person]) =
    people.minBy(dateOfBirth)

  def loadBook(data: String): List[Person] =
    data.split("\n")
      .map(_.trim)
      .map(parsePerson)
      .toList

  def parsePerson(s: String): Person = {
    val Array(name, gender, birthday) = s.split(",").map(_.trim)
    Person(name, gender, birthday)
  }

  def countMales(people: List[Person]): Long =
    people.count(_.gender == "Male")

  def dateOfBirth(person: Person): LocalDate =
    LocalDate.parse(person.birthday, ShortDateFormatter)

  private val ShortDateFormatter = new DateTimeFormatterBuilder()
    .appendPattern("dd/MM/")
    .appendValueReduced(ChronoField.YEAR, 2, 4, 1917)
    .toFormatter()

  implicit val LocalDateOrdering: Ordering[LocalDate] = Ordering.fromLessThan(_ isBefore _)
}
