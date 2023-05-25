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
    val command = new PutXCommand(controller, Hole(HoleX, 3))

    "when doStep" in {
      command.doStep(field) should be(("""
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| X | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
      controller.game.pens1 should be(1)
    }
    "when noStep" in {
      command.noStep(field) should be(("""
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| X | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
    }
    "when undoStep" in {
      command.undoStep(field) should be(("""
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
      controller.game.pens1 should be(2)
    }
    "when redoStep" in {
      command.redoStep(field) should be(("""
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| X | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
      controller.game.pens1 should be(1)
    }
  }
}
