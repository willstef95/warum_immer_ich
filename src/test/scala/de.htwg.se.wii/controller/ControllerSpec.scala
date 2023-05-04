package de.htwg.se.wii
package controller

import model.Field
import model.Hole
import util.Observable

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.wii.controller.Controller

class ControllerSpec extends AnyWordSpec {
  "A wii controller" when {}
  "filled with Empty" should {
    val field = new Field(3, Hole.O)
    val controller = Controller(field, 3)
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

    "Number 4 is O" should {
      "get return Hole O" in {
        controller.get(4) should be(Hole.O)
      }
    }
  }
}
