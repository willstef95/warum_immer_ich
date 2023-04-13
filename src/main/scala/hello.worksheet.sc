import java.io.File

enum Hole:
  case Full, Empty

case class Field(rows: Vector[Vector[Hole]] = [[Full,Empty],[Empty,Full],[Empty,Empty]]):
  def clear(size: Int): Field = copy(Vector.tabulate(size, size) { (row, col) =>
    Hole.Empty
  })
  def cell(row: Int, col: Int): Hole = rows(row)(col)
  val size: Int = rows.size
  // def show(): String = {}

val field = new Field(Vector[])

/** def holestat() = print("Is in process")
  *
  * def eol = sys.props("line.separator") def PgrenzeO_U(b: Int = 2) =
  * "---------" * b + eol def PgrenzeL_R(b: Int = 2) = "| |" def PgrenzeL_RZ(b:
  * Int = 20) = "| " + zahl() + " |" def PgrenzeL_RO(b: Int = 2) = "| O |" def
  * feldLeer(b: Int = 2) = PgrenzeL_R() * b + eol def feldO(b: Int = 2) =
  * PgrenzeL_RO() * b + eol def feldX(b: Int = 2) = PgrenzeL_RZ() * b + eol def
  * zahl(b: Int = 0) = "x" def feld(b: Int = 2) = feldLeer(b) + feldO(b) +
  * feldX(b) + feldLeer(b) def ausgaben(b: Int = 2) = ((PgrenzeO_U(b) + feld(b))
  * * b + PgrenzeO_U(b))
  */
