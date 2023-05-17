package de.htwg.se.wii.model

import de.htwg.se.wii.holes.*

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

  def putX(pos: Int) = {
    val (x, y) = translateW(pos)
    copy(matrix.replaceCell(x, y, Hole(HoleX)))
  }
  def putO(pos: Int) = {
    val (x, y) = translateW(pos)
    copy(matrix.replaceCell(x, y, Hole(HoleO)))
  }

  def get(pos: Int): Hole = {
    val (x, y) = translateW(pos)
    matrix.cell(x, y)
  }
  def translateW(roll: Int) = {
    val y = roll % (size)
    val x =
      if (roll % (size - 1) == 0) roll / size
      else roll / size
    (x, y)
  }
