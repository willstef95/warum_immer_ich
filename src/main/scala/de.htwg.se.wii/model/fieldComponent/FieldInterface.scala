package de.htwg.se.wii.model.FieldComponent

import de.htwg.se.wii.model.MatrixComponent.*
import _root_.de.htwg.se.wii.model.holes.HoleState

trait FieldInterface {
  val matrix: Matrix[HoleState]
  val size: Int
  def bar(cellWidth: Int, cellNum: Int): String
  def cells(row: Int, cellWidth: Int): String
  def mesh(cellWidth: Int): String
  override def toString: String
  def putX(pos: Int): FieldInterface
  def putO(pos: Int): FieldInterface
  def get(pos: Int): HoleState
  def translateW(roll: Int): (Int, Int)
}
