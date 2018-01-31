package uk.filippov.addressbook

object AddressBook {
  def countMales(people: List[Person]): Long =
    people.count(_.gender == "Male")

  case class Person(name: String, gender: String, birthday: String)

}
