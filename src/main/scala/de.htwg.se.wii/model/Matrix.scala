package de.htwg.se.wii.model
import de.htwg.se.wii.model.Matrix.makeFill

case class Matrix[T](rows: Vector[Vector[T]]) {
  def this(size: Int, filling: T) = this(makeFill(size, filling))

  def size: Int = rows.size

  def row(row: Int) = rows(row)

  def cell(row: Int, col: Int) = rows(row)(col)

  def fill(filling: T): Matrix[T] = copy(
    Vector.tabulate(size, size) { (row, col) =>
      filling
    }
  )
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(
    rows.updated(row, rows(row).updated(col, cell))
  )
}

object Matrix {
  private def makeFill[T](size: Int, cell: T): Vector[Vector[T]] =
    Vector.fill(size, size)(cell)
}
