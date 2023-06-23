package de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Inject
import de.htwg.se.wii.model.FieldComponent.{Field, FieldInterface}
import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.holes.HoleO

import scala.xml.PrettyPrinter

class XmlFileIo @Inject() extends FileIOInterface {

  override def loadGame: Game = {
    val file = scala.xml.XML.loadFile("game.xml")
    val names0 = (file \\ "name1" \ "@name1").text
    val names1 = (file \\ "name2" \ "@name2").text
    val pens1 = (file \\ "pens1" \ "@pens1").text
    val pens2 = (file \\ "pens2" \ "@pens2").text
    val roll = (file \\ "roll" \ "@roll").text
    val size = (file \\ "size" \ "@size").text
    var field: FieldInterface = new Field(new Matrix(3, HoleO))

    val holeNodes = (file \ "field" \ "cell")
    //print(holeNodes)
    for (cell <- holeNodes) {
      val pos: Int = (cell \ "@pos").text.toInt
      val holestate = cell.text
      holestate(1) match {
        case 'O' => field = field.putO(pos)
        case 'X' => field = field.putX(pos)
        case _   =>
      }
    }
    val game =
      new Game(field, (names0, names1), pens1.toInt, pens2.toInt, roll.toInt)
    game
  }

  def loadStat: Int = {
    val file = scala.xml.XML.loadFile("game.xml")
    val stat = (file \\ "state" \ "@state").text
    stat.toInt
  }

  def save(game: Game, stat: Int): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(game, stat))
    pw.write(xml)
    pw.close
  }

  def fieldToXml(game: Game, stat: Int) = {
    <entry> {game.toXml()} <state state={stat.toString}></state>
    <field> {
      for {
        pos <- 0 until game.field.size * game.field.size
      } yield cellToXml(game.field, pos)
    }
    </field>
    </entry>
  }

  def cellToXml(field: FieldInterface, pos: Int) = {
    <cell pos={pos.toString}>
      {field.get(pos).toString}
    </cell>
  }
}
