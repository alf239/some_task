package uk.filippov.addressbook

// We read the address book from the file specified as the parameter for our app
object Main extends App {
  val people = AddressBook.loadBook(io.Source.fromFile(args.head, "UTF-8"))

  print("  1. ")
  println(AddressBook.countMales(people))

  print("  2. ")
  println(AddressBook.oldest(people).name)

  val Some(bill) = people.find(_.name.startsWith("Bill "))
  val Some(paul) = people.find(_.name.startsWith("Paul "))

  print("  3. ")
  println(AddressBook.ageDifference(bill, paul))
}
