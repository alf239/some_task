package uk.filippov.addressbook

import java.time.LocalDate
import java.time.format.{DateTimeFormatter, DateTimeFormatterBuilder}
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit.DAYS

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.java8._

import scala.io.BufferedSource

object AddressBook {

  case class Person(name: String, gender: String, birthday: LocalDate)

  def ageDifferenceInDays(a: Person, b: Person): Long =
    DAYS.between(a.birthday, b.birthday)

  def oldest(people: List[Person]): Person =
    people.minBy(_.birthday)

  def loadBook(source: BufferedSource): List[Person] = {
    val reader: CsvReader[ReadResult[Person]] =
      source.mkString.asCsvReader[Person](rfc)
    reader.collect {
      case Success(person) => person
    }.toList
  }

  def countMales(people: List[Person]): Long =
    people.count(_.gender == "Male")

  private val Space: DateTimeFormatter =
    new DateTimeFormatterBuilder().appendLiteral(" ").toFormatter

  private val ShortDateFormatter = new DateTimeFormatterBuilder()
    .appendOptional(Space)
    .appendPattern("dd/MM/")
    .appendValueReduced(ChronoField.YEAR, 2, 4, 1917)
    .toFormatter()

  implicit val localDateOrdering: Ordering[LocalDate] =
    Ordering.fromLessThan(_ isBefore _)

  implicit val customDateDecoder: CellDecoder[LocalDate] =
    localDateDecoder(ShortDateFormatter)

  implicit val personDecoder: RowDecoder[Person] =
    RowDecoder.decoder(0, 1, 2) { (name: String, gender: String, birthday: LocalDate) =>
      Person(name.trim(), gender.trim, birthday)
    }
}
