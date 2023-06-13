package de.htwg.se.wii.model.fieldComponent

import _root_.de.htwg.se.wii.model.holes.HoleState

trait FieldInterface {
  def bar(cellWidth: Int, cellNum: Int): String
  def cells(row: Int, cellWidth: Int): String
  def mesh(cellWidth: Int): String
  override def toString: String
  def putX(pos: Int): Unit
  def putO(pos: Int): Unit
  def get(pos: Int): HoleState
  def translateW(roll: Int): (Int, Int)

}

trait MatrixInterface[T] {
  def size: Int
  def row(row: Int): Vector[T]
  def cell(row: Int, col: Int): Vector[T]
  def fill(filling: T): MatrixInterface[T]
  def replaceCell(row: Int, col: Int, cell: T): MatrixInterface[T]

}
