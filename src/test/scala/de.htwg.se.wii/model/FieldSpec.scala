package de.htwg.se.wii.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
  "A TicTacToe Field" when {
    "filled with X" should {
      val field1 = new Field(1, Hole.X)
      val field2 = new Field(2, Hole.X)
      val field3 = new Field(3, Hole.X)
      val eol = sys.props("line.separator")
      "have a bar as String of form '+---+---+---+'" in {
        field3.bar() should be("+---+---+---+" + eol)
      }
      "have a scalable bar" in {
        field1.bar(1, 1) should be("+-+" + eol)
        field2.bar(2, 1) should be("+--+" + eol)
        field2.bar(1, 2) should be("+-+-+" + eol)
      }
      "have cells as String of form '| X | X | X |'" in {
        field3.cells(0) should be("| X | X | X |" + eol)
      }
      "have scalable cells" in {
        field1.cells(0, 1) should be("|X|" + eol)
        field2.cells(0, 1) should be("|X|X|" + eol)
        field1.cells(0, 3) should be("| X |" + eol)
      }
      "have a mesh in the form " +
        "+-+" +
        "|X|" +
        "+-+" in {
          field1.mesh(1) should be("+-+" + eol + "|X|" + eol + "+-+" + eol)
          field2.mesh(1) should be(
            "+-+-+" + eol + "|X|X|" + eol + "+-+-+" + eol + "|X|X|" + eol + "+-+-+" + eol
          )
        }
    }
    "filled with O" should {
      val field1 = new Field(1, Hole.O)
      val field2 = new Field(2, Hole.O)
      val field3 = new Field(3, Hole.O)
      val eol = sys.props("line.separator")
      "have a bar as String of form '+---+---+---+'" in {
        field3.bar() should be("+---+---+---+" + eol)
      }
      "have a scalable bar" in {
        field1.bar(1, 1) should be("+-+" + eol)
        field2.bar(2, 1) should be("+--+" + eol)
        field2.bar(1, 2) should be("+-+-+" + eol)
      }
      "have cells as String of form '| O | O | O |'" in {
        field3.cells(0) should be("| O | O | O |" + eol)
      }
      "have scalable cells" in {
        field1.cells(0, 1) should be("|O|" + eol)
        field2.cells(0, 1) should be("|O|O|" + eol)
        field1.cells(0, 3) should be("| O |" + eol)
      }
      "have a mesh in the form " +
        "+-+" +
        "|O|" +
        "+-+" in {
          field1.mesh(1) should be("+-+" + eol + "|O|" + eol + "+-+" + eol)
          field2.mesh(1) should be(
            "+-+-+" + eol + "|O|O|" + eol + "+-+-+" + eol + "|O|O|" + eol + "+-+-+" + eol
          )
        }
    }
    "filled with Empty" should {
      var field = new Field(3, Hole.O)
      "be initiall with O" in {
        field.toString should be(("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#'))
      }
      "have an O" in {
        field.putO(4).toString should be(
          ("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#')
        )
      }
      "have an X after two put" in {
        field.putX(4).toString should be(
          ("""#+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #| O | X | O |
          #+---+---+---+
          #| O | O | O |
          #+---+---+---+
          #""").stripMargin('#')
        )
      }

      "Number 4 is O" should {
        "get return Hole O" in {
          field.get(4) should be(Hole.O)
        }
      }
    }

    "roll is 6" should {
      val field = new Field(3, Hole.O)
      "translate return (2,0)" in {
        field.translateW(6) should be(2, 0)
      }
    }
  }
}
