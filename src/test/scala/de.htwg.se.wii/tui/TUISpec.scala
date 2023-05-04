package de.htwg.se.wii
package tui

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.model.Field
import de.htwg.se.wii.model.Hole
import de.htwg.se.wii.controller.Controller

class TUISpec extends AnyWordSpec {

  "the tui" should {

    var field = new Field(3, Hole.O)
    val controller = Controller(field, 3)
    val tui = TUI(controller, 3)
    controller.put(Hole.X, 2)

    "get Hole.x back" in {
      controller.get(2) should be(Hole.X)
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

  }

}

//   "not update the field with an invalid input" in {
//     val input = "invalid\n"
//     val in = new java.io.StringReader(input)
//     Console.withIn(in) {
//       tui.gameLoop()
//     }
//     controller.get(1) shouldEqual Hole.Empty
//   }

//   "exit the game loop when 'q' is entered" in {
//     val input = "q\n"
//     val in = new java.io.StringReader(input)
//     Console.withIn(in) {
//       tui.gameLoop()
//     }
//     controller.get(1) shouldEqual Hole.Empty
//   }
