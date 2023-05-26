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
import de.htwg.se.wii.util.Command

class PutOCommandSpec extends AnyWordSpec {
  "A PutOCommandSpec" when {}
  "filled with Empty" should {
    val field = new Field()
    val controller = Controller(field, 3)
    var game = new Game(("Spieler1", "Spieler2"), 2, 2)
    val command = new PutOCommand(controller, Hole(HoleO, 3))
    val command1 = new PutXCommand(controller, Hole(HoleX, 3))
    controller.doAndPublish(controller.putO, Hole(HoleO, 3))
    controller.doAndPublish(controller.putX, Hole(HoleX, 3))

    controller.undoManager.doStep(
      field,
      PutXCommand(controller, Hole(HoleO, 3))
    )
    controller.undoStep()

    command1.doStep(field)

    "when doStep" in {
      undoManager.undoStep(field)
      controller.get(3) should be(HoleO)
      controller.game.pens2 should be(3)
    }
    "when noStep" in {
      command.noStep(field)
      controller.get(3) should be(HoleO)
      controller.game.pens1 should be(3)
    }
    "when undoStep" in {
      command.undoStep(field)
      controller.get(3) should be(HoleX)
      controller.game.pens1 should be(2)
    }
    "when redoStep" in {
      command.redoStep(field)
      controller.get(3) should be(HoleO)
      controller.game.pens1 should be(3)
    }
  }
}
