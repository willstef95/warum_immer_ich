package de.htwg.se.wii.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
// import de.htwg.se.wii.model.Game.field
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.holes.HoleO
import scala.io.Source
// import play.api.libs.json.JsValue
import play.api.libs.json.*

class FileIO extends FileIOInterface:

  override def save(game: Game): Unit = {
    print("json save")
    import java.io._
    val pw = new PrintWriter(new File("field.json"))
    pw.write(ToJson(game))
    pw.close
  }

  def ToJson(game: Game) = {
    print("   tojson       ")
    import play.api.libs.json._
    Json.prettyPrint(
      Json.obj(
        "size" -> game.field.size,
        "pens1" -> game.pens1,
        "pens2" -> game.pens2,
        "name1" -> game.names(0),
        "name2" -> game.names(1)
      )
    )
  }

//   override def load: Game = {
// val source: String = Source.fromFile("field.json").getLines.mkString
// val json: JsValue = Json.parse(source)
// val size = (json \ "field" \ "size").get.toString.toInt
// val playerState = (json \ "field" \ "playerState").get.toString

// var field: FieldInterface = new Field(new Matrix(size, HoleO))

// for (index <- 0 until size * size) {
//   val row = (json \\ "row")(index).as[Int]
//   val col = (json \\ "col")(index).as[Int]
//   val value = (json \\ "cell")(index).as[String]
//   value match {
//     case "O" => field = field.putO(pos)
//     case "X" => field = field.putX(pos)
//     case _   =>
//   }
// }
// (field, player)
//   }
