print(feld(3))
print(cells())

var eol = sys.props("line.separator")

def bar(cellWidth: Int, cellNum: Int): String = {
  (("+" + "-" * cellWidth) * cellNum) + "+" + eol
}

def cells(row: Int = 3, cellWidth: Int = 3): String = {
  (("|" + (" " + HoleO.toString + " ")) * row) + "|" + eol
}

def feld(colums: Int): String = {
  bar(3, 3) + cells()
}

case class Hole(val state: HoleState) {
  def renderText(): String = state.renderText()
  // override def toString(): String = ???
}

trait HoleState {
  def renderText(): String
  // def toString(): String
}

object HoleO extends HoleState {
  override def renderText(): String = "O"
  override def toString(): String = "O"
}

object HoleX extends HoleState {
  override def renderText(): String = "X"
  override def toString(): String = "X"
}
