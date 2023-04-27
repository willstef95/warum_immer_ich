package de.htwg.se.wii.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.wii.controller.Controller
import de.htwg.se.wii.aview.TUI

class ObserverSpec extends AnyWordSpec {
  "A Observer" when {
    "add of tui" should {
      val field = new Field(3, Hole.O)
      val controller = Controller(field)
      val tui = TUI(controller, 3)
      "return tui" in {
        controller.add(tui) should be(tui)
      }
    }
  }
}
