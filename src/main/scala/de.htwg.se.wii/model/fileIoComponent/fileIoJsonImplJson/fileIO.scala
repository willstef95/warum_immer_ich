package de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.Game.field
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.holes.HoleO
import play.api.libs.json.*

class FileIO extends FileIOInterface:
 override def load: Game = {
    val source: String = Source.fromFile("field.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "field" \ "size").get.toString.toInt
    
    val playerState = (json \ "field" \ "playerState").get.toString


    var field: FieldInterface = new Field(new Matrix(size,HoleO))

    for (index <- 0 until size * size) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val value = (json \\ "cell")(index).as[String]
      value match {
        case "O" => field = field.putO(pos)
        case "X" => field = field.putX(pos)
        case _ =>
      }
    }
    (field, player)
  }

  override def save(field: FieldInterface, player: PlayerState): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.json"))
    pw.write(Json.prettyPrint(fieldToJson(field, player)))
    pw.close
  }

  def fieldToJson(field: FieldInterface, player: PlayerState) = {
    Json.obj(
      "field" -> Json.obj(
        "size" -> JsNumber(field.size),
        "playerState" -> player.getStone.toString,
        "cells" -> Json.toJson(
          for {
            row <- 1 until field.size + 1
            col <- 1 until field.size + 1
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> field.get(row, col).toString
            )
          }
        )
      )
    )
  }
}cell pos={pos.toString}>
    {field.get(pos).toString}
    </cell>
  }
         
