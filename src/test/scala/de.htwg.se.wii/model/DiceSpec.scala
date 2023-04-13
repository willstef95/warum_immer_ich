package de.htwg.se.wii.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DiceSpec extends AnyWordSpec with Matchers {

  "Dice" when {

    "not set to any value " should {
      val dice = Dice()
      val num = dice.roll()
      "have value between 1-6" in {
        num should (be >= 1 and be <= 6)
      }
    }

  }
}
