package de.htwg.se.wii.controller

import de.htwg.se.wii.model.FieldComponent._
import de.htwg.se.wii.model.holes._
import de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl.XmlFileIo
import de.htwg.se.wii.controller.controllerComponent.*
import de.htwg.se.wii.model.Game
import de.htwg.se.wii.model.Dice
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.util.Observable
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ControllerSpec extends AnyWordSpec {
  "A wii controller" when {}
  "filled with Empty" should {
    val field = new Field(new Matrix(3, HoleO))
    val fileIo = new XmlFileIo
    val controller = Controller(field, fileIo, 3)
    var game = new Game(field, ("Spieler1", "Spieler2"), 2, 2, 0)

    "be initiall with O" in {
      controller.toString should be(("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
    }

    // PutXCommandTesten für Spieler 2
    "With HoleO on 0! " in {
      controller.doAndPublish(controller.putX, Hole(HoleX, 0), 2)
      controller.get(0) should be(HoleO)
    }

    "With HoleX on 1" in {
      controller.doAndPublish(controller.putX, Hole(HoleX, 1), 2)
      controller.get(1) should be(HoleX)
    }

    "undo" in {
      controller.doAndPublish(controller.undo)
      controller.get(1) should be(HoleO)
    }

    "redo" in {
      controller.doAndPublish(controller.redo)
      controller.get(1) should be(HoleX)
    }

    // PutXCommandTesten für Spieler 1
    // "-With HoleO on 0! " in {
    //   controller.doAndPublish(controller.putX, Hole(HoleX, 0), 1)
    //   controller.get(0) should be(HoleO)
    // }

    "-With HoleX on 1" in {
      controller.doAndPublish(controller.putX, Hole(HoleX, 1), 1)
      controller.get(1) should be(HoleX)
    }

    "-undo" in {
      controller.doAndPublish(controller.undo)
      controller.get(1) should be(HoleO)
    }

    "-redo" in {
      controller.doAndPublish(controller.redo)
      controller.get(1) should be(HoleX)
    }

    // PutOCommand für Spieler 2

    "-With HoleX on 1 Player2" in {
      controller.doAndPublish(controller.putX, Hole(HoleO, 1), 2)
      controller.doAndPublish(controller.putO, Hole(HoleO, 1), 2)
      controller.get(1) should be(HoleO)
    }

    "undo Player2 and x" in {
      controller.doAndPublish(controller.undo)
      controller.get(1) should be(HoleX)
    }

    "redo Player 2 and x" in {
      controller.doAndPublish(controller.redo)
      controller.get(1) should be(HoleO)
    }

    // PutOCommand für Spieler 1

    "-With HoleX on 1 Player1" in {
      controller.doAndPublish(controller.putX, Hole(HoleO, 1), 1)
      controller.doAndPublish(controller.putO, Hole(HoleO, 1), 1)
      controller.get(1) should be(HoleO)
    }

    "undo Player1 and x" in {
      controller.doAndPublish(controller.undo)
      controller.get(1) should be(HoleX)
    }

    "redo Player1 and x" in {
      controller.doAndPublish(controller.redo)
      controller.get(1) should be(HoleO)
    }

    // "2. With HoleX on 3" in {

    // "pens down" should {
    //   "get return n-1" in {
    //     controller.pensdown(1) should be(1)
    //     controller.pensdown(2) should be(1)

    //   }
    // }
    // "pens up" should {
    //   "get return n+1" in {
    //     controller.pensup(1) should be(2)
    //     controller.pensup(2) should be(2)

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
