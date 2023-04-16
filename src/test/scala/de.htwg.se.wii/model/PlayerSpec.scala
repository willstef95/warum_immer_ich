package de.htwg.se.wii.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {

  "Player" when {

    "not set to no value " should {
      val player = Player()
      val stat = player.stat()
      val show = player.show()
      "have 7 Strips" in {
        stat should be(7)
        show should endWith("|||||||")
      }
    }

    "not set to any value " should {
      val player = Player(3)
      val stat = player.stat()
      val show = player.show()
      "have 7 Strips" in {
        stat should be()
        show should endWith("|||||||")
      }
    }

  }
}
