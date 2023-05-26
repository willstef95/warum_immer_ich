package de.htwg.se.wii
package tui

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.model.Field
import de.htwg.se.wii.model.holes.*
import de.htwg.se.wii.controller.*
import de.htwg.se.wii.model.Game
import java.util.Observer

class TUISpec extends AnyWordSpec {

  var field = new Field()
  val controller = Controller(field, 3)
  val tui = new TUI(controller, 3)
  var game = new Game(("Spieler1", "Spieler2"), 1, 0)

//   "init is set to Stefan and Hannes" in {
//     tui.init() should be("Stefan", "Hannes")
//   }
  controller.doAndPublish(controller.putX, Hole(HoleX, 2), 1)
  controller.doAndPublish(controller.putX, Hole(HoleX, 5), 1)

  "the tui" should {
    "0 gewurfelt" in {
      tui.wurf(0) should be(None)
    }

    "Text ausgegeben" in {
      tui.printt() should be(true)
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
      tui.roll0(0) should be(true)
    }
    "not 0 diced" in {
      tui.rollNot0(2) should be(true)
    }

    "field set on O" in {
      tui.oSetzen(2) should be(true)
    }
    "field set on X" in {
      tui.xSetzen(1) should be(true)
    }
    "name spieler 1 abfragen" in {
      controller.game.names(0) should be("Spieler1")
    }

    "One players has 0pins" in {
      tui.isFinish() should be(false)
    }
  }
  "A TUI" when {
    "tui exit" in {
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
  "A TUI" when {
    "None player has 0 pins" in {
      var game = new Game(("Spieler1", "Spieler2"), 1, 2)
      tui.isFinish() should be(true)
      tui.isFinish() should be(true)
    }
  }

}
