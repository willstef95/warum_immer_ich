package de.htwg.se.wii.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {

  "Player" when {

    "not set to any value " should {
      val player = Player()
      val stat = player.stat()
      "have 7 Strips" in {
        stat should be(7)
      }
    }
    "not set to any value " should {
      val player = Player()
      val show = player.show()
      "have 7 Strips" in {
        stat should endWith("|||||||")
      }
    }

  }
}
