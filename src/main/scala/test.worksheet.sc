import java.io.File

class Person(name: String, age: Int) {
  def toXml() = <person><name>{name}</name><age>{age}</age></person>
}
object xml {
  val people = List(new Person("Alice", 16), new Person("Bob", 64))
  val data = <people>{people.map(p => p.toXml())}</people>
}
println(xml.data)
val names = xml.data \\ "name"
names.text
