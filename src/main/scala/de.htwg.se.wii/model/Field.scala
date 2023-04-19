package de.htwg.se.wii.model

case class Field(matrix: Matrix[Hole]):
  def this(size: Int, filling: Hole) = this(new Matrix(size, filling))
  val size = matrix.size
  val eol = sys.props("line.separator")
  def bar(cellWidth: Int = 3, cellNum: Int = 3): String =
    (("+" + "-" * cellWidth) * cellNum) + "+" + eol
  def cells(row: Int, cellWidth: Int = 3): String =
    matrix
      .row(row)
      .map(_.toString)
      .map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2))
      .mkString("|", "|", "|") + eol
  def mesh(cellWidth: Int = 3): String =
    (0 until size)
      .map(cells(_, cellWidth))
      .mkString(
        bar(cellWidth, size),
        bar(cellWidth, size),
        bar(cellWidth, size)
      )
  override def toString = mesh()
  def put(hole: Hole, x: Int, y: Int) = copy(matrix.replaceCell(y, x, hole))
  def get(x: Int, y: Int): Hole = matrix.cell(x, y)
