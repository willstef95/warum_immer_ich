package de.htwg.se.wii.tui

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.model.FieldComponent._
import de.htwg.se.wii.model.holes._
import de.htwg.se.wii.controller.controllerComponent.Controller
import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl.XmlFileIo
import java.util.Observer
import de.htwg.se.wii.model.MatrixComponent.Matrix

class TUISpec extends AnyWordSpec {

  val field = new Field(new Matrix(3, HoleO))
  val fileIo = new XmlFileIo
  val controller = Controller(field, fileIo, 3)
  val tui = new TUI(controller)
  var game = new Game(field, ("Spieler1", "Spieler2"), 1, 0, 0)

  controller.doAndPublish(controller.putX, Hole(HoleX, 2), 1)
  controller.doAndPublish(controller.putX, Hole(HoleX, 5), 1)

  "the tui" should {
    "0 gewurfelt" in {
      controller.wurf(0) should be(None)
    }

    "Text ausgegeben" in {
      tui.printGameStat() should be(true)
    }

    "get Hole.x back" in {
      controller.get(2) should be(HoleX)
    }
    "gameloop runs and 2 is a X " in {
      controller.toString should be(("""#+---+---+---+
          #| O | O | X |
          #+---+---+---+
          #| O | O | X |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
    }
    "diced 0" in {
      controller.roll0(0) should be(true)
    }
    "not 0 diced" in {
      controller.rollNot0(2) should be(true)
    }

    "field set on O" in {
      controller.oSetzen(2) should be(true)
    }
    "field set on X" in {
      controller.xSetzen(1) should be(true)
    }

    "name spieler 1 abfragen" in {
      controller.game.names(0) should be("Spieler1")
    }

    // "One players has 0pins" in {
    //   controller.isFinish() should be(false)
    // }
  }
  "A controller" when {
    "controller exit" in {
      tui.processInput("q") should be(false)
    }
    "tui runs" in {
      tui.processInput("w") should be(true)
    }
    "tui z" in {
      tui.processInput("z") should be(true)
    }
    "tui y" in {
      tui.processInput("y") should be(true)
    }
  }
  // "A TUI" when {
  //   "None player has 0 pins" in {
  //     var game = new Game(("Spieler1", "Spieler2"), 1, 2, 0)
  //     tui.isFinish() should be(true)
  //     tui.isFinish() should be(true)
  //   }
  // }

}
