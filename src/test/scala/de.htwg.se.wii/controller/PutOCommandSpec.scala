package de.htwg.se.wii
package controller

import model.Field
import model.holes.*
import util.Observable

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.wii.controller.Controller
import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.Dice
import de.htwg.se.wii.util.Stat

class PutXCommandSpec extends AnyWordSpec {
  "A PutXCommandSpec" when {}
  "filled with Empty" should {
    val field = new Field()
    val controller = Controller(field, 3)
    var game = new Game(("Spieler1", "Spieler2"), 2, 2)
    val command1 = new PutXCommand(controller, Hole(HoleX, 3))
    command1.doStep(field)
    val command = new PutOCommand(controller, Hole(HoleO, 3))

    "when doStep" in {
      command.doStep(field)
      controller.game.pens1 should be(3)
      controller.toString should be(("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
    }
    "when noStep" in {
      controller.toString should be(("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
    }
    "when undoStep" in {
      command.undoStep(field)
      controller.game.pens1 should be(2)
      controller.toString should be(("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| X | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
    }
    "when redoStep" in {
      command.redoStep(field)
      controller.game.pens1 should be(3)
      controller.toString should be(("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
    }
  }
}
