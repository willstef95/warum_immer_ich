package de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.Game.field
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.holes.HoleO

class FileIO extends FileIOInterface:

  override def save(game: Game): Unit =
    scala.xml.XML.save("game.xml", game.toXml())

  override def load: Game = {
    val file = scala.xml.XML.loadFile("game.xml")
    // val field = (file \ "field")
    // val names = (file \ "names")
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

    val game = new Game(field, names, pens1, pens2, roll)
  }
