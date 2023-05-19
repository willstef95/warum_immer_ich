package de.htwg.se.wii
package tui

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.model.Field
import de.htwg.se.wii.model.holes.*
import de.htwg.se.wii.controller.Controller
import de.htwg.se.wii.model.Game

class TUISpec extends AnyWordSpec {

  var field = new Field()
  val controller = Controller(field, 3)
  val tui = new TUI(controller, 3)
  var game = new Game(("Spieler1", "Spieler2"), 2, 2, 0)

  controller.putX(2)
  controller.putO(3)

  "the tui" should {

    "get Hole.x back" in {
      controller.get(2) should be(HoleX)
    }
    "gameloop runs and 2 is a X " in {
      controller.toString should be(("""#+---+---+---+
          #| O | O | X |
          #+---+---+---+
          #| O | O | O |
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
    "print nothing on showpin=0" in {
      tui.ShowPins.executeShowPins(0) should be(true)
      tui.ShowPins.dontshow(0) should be(" ")
    }

    // "name spieler 1 abfragen" in {
    //   controller.game.names(0) should be("Spieler1")
    // }

  }
  "A TUI" when {
    "tui exit" in {
      tui.processInput("q") should be(false)
    }
    "tui runs" in {
      tui.processInput(" ") should be(true)
    }
  }
}
