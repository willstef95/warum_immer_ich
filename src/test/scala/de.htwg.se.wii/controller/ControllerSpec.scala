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

class ControllerSpec extends AnyWordSpec {
  "A wii controller" when {}
  "filled with Empty" should {
    val field = new Field()
    val controller = Controller(field, 3)
    var game = new Game(("Spieler1", "Spieler2"), 2, 2)

    "be initiall with O" in {
      controller.get(3) should be(HoleO)
    }

    "With HoleX on 3" in {
      controller.doAndPublish(controller.putX, Hole(HoleX, 3))
      controller.get(3) should be(HoleX)
    }

    // "undo" in {
    //   controller.doAndPublish(controller.undo)
    //   controller.toString should be(("""
    //       #+---+---+---+
    //       #| O | O | O |
    //       #+---+---+---+
    //       #| O | O | O |
    //       #+---+---+---+
    //       #| O | O | O |
    //       #+---+---+---+
    //       #""").stripMargin('#'))
    // }
    // "redo" in {
    //   controller.doAndPublish(controller.redo)
    //   controller.toString should be(("""
    //       #+---+---+---+
    //       #| O | O | O |
    //       #+---+---+---+
    //       #| X | O | O |
    //       #+---+---+---+
    //       #| O | O | O |
    //       #+---+---+---+
    //       #""").stripMargin('#'))
    // }

    "pens down" should {
      "get return n-1" in {
        controller.pensdown(1) should be(0)
        controller.pensdown(2) should be(1)

      }
    }
    "pens up" should {
      "get return n+1" in {
        controller.pensup(1) should be(1)
        controller.pensup(2) should be(2)

      }
    }
    "not set to any value " should {
      val num = controller.roll()
      "have value between 0-8" in {
        num should (be >= 0 and be <= 8)
      }
    }
    "init is set to Stefan and Hannes" should {
      controller.init("Stefan", "Hannes")
      "game has those names" in {
        controller.game.names should be("Stefan", "Hannes")
      }
    }
  }
}
