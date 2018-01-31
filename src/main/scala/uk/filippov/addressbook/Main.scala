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

    val Some(bill) = people.find(_.name.startsWith("Bill "))
    val Some(paul) = people.find(_.name.startsWith("Paul "))

    print("  3. ")
    println(AddressBook.ageDifferenceInDays(bill, paul))
  }
}
