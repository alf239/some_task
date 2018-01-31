package uk.filippov.addressbook

import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit.DAYS

import kantan.csv._
import kantan.csv.ops._

import scala.io.BufferedSource

object AddressBook {

  case class Person(name: String, gender: String, birthday: String)

  def ageDifference(a: Person, b: Person): Long =
    DAYS.between(dateOfBirth(a), dateOfBirth(b))

  def oldest(people: List[Person]): Person =
    people.minBy(dateOfBirth)

  def loadBook(source: BufferedSource): List[Person] = {
    val reader: CsvReader[ReadResult[Person]] =
      source.mkString.asCsvReader[Person](rfc)
    reader.collect {
      case Success(person) => person
    }.toList
  }

  def countMales(people: List[Person]): Long =
    people.count(_.gender == "Male")

  def dateOfBirth(person: Person): LocalDate =
    LocalDate.parse(person.birthday, ShortDateFormatter)

  private val ShortDateFormatter = new DateTimeFormatterBuilder()
    .appendPattern("dd/MM/")
    .appendValueReduced(ChronoField.YEAR, 2, 4, 1917)
    .toFormatter()

  implicit val localDateOrdering: Ordering[LocalDate] = Ordering.fromLessThan(_ isBefore _)

  implicit val personDecoder: RowDecoder[Person] =
    RowDecoder.decoder(0, 1, 2) { (name: String, gender: String, birthday: String) =>
      Person(name.trim(), gender.trim, birthday.trim)
    }
}
