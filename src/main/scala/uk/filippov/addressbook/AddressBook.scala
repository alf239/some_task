package uk.filippov.addressbook

import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object AddressBook {
  def oldest(people: List[Person]) =
    people.minBy(_.dateOfBirth)

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

  case class Person(name: String, gender: String, birthday: String) {
    val dateOfBirth: LocalDate =
      LocalDate.parse(birthday, new DateTimeFormatterBuilder()
        .appendPattern("dd/MM/")
        .appendValueReduced(ChronoField.YEAR, 2, 4, 1917)
        .toFormatter())
  }

  implicit val LocalDateOrdering: Ordering[LocalDate] = Ordering.fromLessThan(_ isBefore _)
}
