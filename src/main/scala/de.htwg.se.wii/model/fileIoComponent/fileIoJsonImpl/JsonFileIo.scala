package de.htwg.se.wii.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.holes.HoleO
import scala.io.Source
import play.api.libs.json._
import scala.util.control._
import scala.util.control.Breaks

class JsonFileIo extends FileIOInterface {

  override def save(game: Game, stat: Int): Unit = {
    // print("json save")
    import java.io._
    val pw = new PrintWriter(new File("field.json"))
    pw.write(ToJson(game, stat))
    pw.close
  }

  def ToJson(game: Game, stat: Int) = {
    // print("   tojson       ")
    import play.api.libs.json._

    var str = game.field.get(0).toString
    var x = 1
    while (x < 9) {
      var str2 = game.field.get(x).toString
      str = s"$str$str2"
      x = x + 1;
    }

    // print(str)

    Json.prettyPrint(
      Json.obj(
        "size" -> game.field.size,
        "pens1" -> game.pens1,
        "pens2" -> game.pens2,
        "name1" -> game.names(0),
        "name2" -> game.names(1),
        "statfield" -> str,
        "stat" -> stat,
        "roll" -> game.roll
      )
    )
  }

  def loadStat: Int = {
    import java.io._
    val source: String = Source.fromFile("field.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val stat = (json \ "stat").get.toString.toInt
    stat
  }

  def loadGame: Game = {
    val source: String = Source.fromFile("field.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val pens1 = (json \ "pens1").get.toString.toInt
    val pens2 = (json \ "pens2").get.toString.toInt
    val name1 = (json \ "name1").get.toString
    val name2 = (json \ "name2").get.toString
    val statfield = (json \ "statfield").get.toString
    val size = (json \ "size").get.toString.toInt
    val roll = (json \ "roll").get.toString.toInt
    val eol = sys.props("line.separator")
    var field: FieldInterface = new Field(new Matrix(size, HoleO))

    var pos = 0
    val loop = new Breaks
    loop.breakable {
      for (cell <- statfield) {
        if (pos < size * size)
          if (cell == 'O') {
            field = field.putO(pos)
            pos = pos + 1;
          } else if (cell == 'X') {
            field = field.putX(pos)
            pos = pos + 1;

          }
        else loop.break
      }
    }
    val game = new Game(field, (name1, name2), pens1, pens2, 0)
    game
  }
}
