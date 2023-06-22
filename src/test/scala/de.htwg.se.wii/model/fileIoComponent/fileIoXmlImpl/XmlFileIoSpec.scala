package de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.model.FieldComponent._
import de.htwg.se.wii.model.holes._
import de.htwg.se.wii.controller._
import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl.XmlFileIo
import java.util.Observer
import de.htwg.se.wii.model.MatrixComponent.Matrix

class XmlFileIoSpec extends AnyWordSpec with Matchers {

  val field = new Field(new Matrix(3, HoleO))
  val fileIo = new XmlFileIo
  val controller = Controller(field, fileIo, 3)
  val tui = new TUI(controller)
  var game = new Game(field, ("Spieler1", "Spieler2"), 1, 0, 0)

  controller.doAndPublish(controller.putX, Hole(HoleX, 2), 1)
  controller.doAndPublish(controller.putX, Hole(HoleX, 5), 1)

  "Xml" when {
    "loadGame is running it" should {
      val game = fileIo.loadGame
      "have returned" in {
        game.names(0) shouldBe a [String]
      }
    }
  }
  "Xml" when {
    "loadStat is running it" should {
      val state = fileIo.loadStat
      "have returned" in {
        state shouldBe a [Int]
      }
    }
  }
  "Xml" when {
    "fieldToXml is running it" should {
      val elem = fileIo.fieldToXml(game,1)
      "have returned" in {
        elem.text shouldBe a [String]
      }
    }
  }
  "Xml" when {
    "cellToXml is running it" should {
      val cell = fileIo.cellToXml(field, 1)
      "have returned" in {
        cell.text shouldBe a [String]
      }
    }
  }
}
