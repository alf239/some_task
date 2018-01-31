package uk.filippov.addressbook

object AddressBook {
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

  case class Person(name: String, gender: String, birthday: String)

}
