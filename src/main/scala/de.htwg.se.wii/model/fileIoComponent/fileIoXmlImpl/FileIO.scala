package de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.Game.field
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.holes.HoleO
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface:

  override def load: Game = {
    val file = scala.xml.XML.loadFile("game.xml")
    // val field = (file \ "field")
    val names0 = (file \ "names(0)").text
    val names1 = (file \ "names(1)").text
    val pens1 = (file \ "pens1").text.toInt
    val pens2 = (file \ "pens2").text.toInt
    val roll = (file \ "roll").text.toInt
    val sizeAttr = (file \\ "field" \ "@size")
    val size = sizeAttr.text.toInt
    var field: FieldInterface = new Field(new Matrix(size, HoleO))

    val holeNodes = (file \\ "cell")
    for (cell <- holeNodes) {
      val pos: Int = (cell \ "@pos").text.toInt
      val value: String = cell.text.trim.toString
      value match {
        case "O" => field = field.putO(pos)
        case "X" => field = field.putX(pos)
        case _   =>
      }
    }

    val game = new Game(field, (names0, names1), pens1, pens2, roll)
    game
  }

  override def save(game: Game): Unit =
    saveString(game)

  def saveXML(game: Game): Unit = {
    scala.xml.XML.save("game.xml", fieldToXml(game.field, game.names))
  }

  def saveString(game: Game): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(game.field, game.names))
    pw.write(xml)
    pw.close
  }
  def fieldToXml(field: FieldInterface, names: (String, String)) = {
    <field size={field.size.toString}>
      {
        <names(0)>{names[0]}</names(0)>
        <names(1)>{names[1]}</names(1)>
      for {
        pos <- 0 until field.size
      } yield cellToXml(field, pos)
    }
    </field>
  }

  def cellToXml(field: FieldInterface, pos: Int) = {
    <cell pos={pos.toString}>
    {field.get(pos).toString}
    </cell>
  }
           