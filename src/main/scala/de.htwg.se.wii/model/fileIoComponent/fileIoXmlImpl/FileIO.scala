package de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Inject
import de.htwg.se.wii.model.FieldComponent.{Field, FieldInterface}
import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.holes.HoleO

import scala.xml.PrettyPrinter

class FileIO @Inject() extends FileIOInterface {

  override def load: Game = {
    val file = scala.xml.XML.loadFile("game.xml")
    val names0 = (file \ "name1").text
    val names1 = (file \ "name2").text
    val pens1 = (file \ "pens1").text.toInt
    val pens2 = (file \ "pens2").text.toInt
    val roll = (file \ "roll").text.toInt
    val size = (file \\ "field").text.toInt
    var field: FieldInterface = new Field(new Matrix(size, HoleO))
    new Matrix(size, HoleO)

    val holeNodes = (file \\ "cell")
    for (cell <- holeNodes) {
      val pos: Int = (cell \ "@pos").text.toInt
      val holestate: String = cell.text
      holestate match {
        case "O" => field = field.putO(pos)
        case "X" => field = field.putX(pos)
        case _ =>
      }
    }
    val game = new Game(field, (names0, names1), pens1, pens2, roll)
    game
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
    <entry> { game.toXml() } <state> { stat.toString } </state>
    <field> {
    for {
      pos <- 0 until game.field.size * game.field.size
    } yield cellToXml(game.field, pos)}
    </field>
    </entry>}

  def cellToXml(field: FieldInterface, pos: Int) = {
    <cell pos={pos.toString}>
      {field.get(pos).toString}
    </cell>
  }
}