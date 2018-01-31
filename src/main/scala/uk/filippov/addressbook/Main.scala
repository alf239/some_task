package uk.filippov.addressbook

object Main extends App {
  if (args.length != 1) {
    println("Usage: sbt \"run ./AddressBook\"")
  } else {
    val inputFile = args.head

    val people = AddressBook.loadBook(io.Source.fromFile(inputFile, "UTF-8"))

    print("  1. ")
    println(AddressBook.countMales(people))

    print("  2. ")
    println(AddressBook.oldest(people).name)

    val ageDiff = for {
      bill <- people.find(_.name.startsWith("Bill "))
      paul <- people.find(_.name.startsWith("Paul "))
    } yield AddressBook.ageDifferenceInDays(bill, paul)

    print("  3. ")
    println(ageDiff.map(_.toString).getOrElse("Not found"))
  }
}
