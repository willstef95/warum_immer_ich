package de.htwg.se.wii.model

case class Field(cellNumero: Int):
  def eol = sys.props("line.separator")
  // defines the bar
  def bar(cellWidth: Int = 3, cellNum: Int = 3) =
    (("+" + "-" * cellWidth) * cellNum) + "+" + eol
  // defines the cells
  def cells(cellWidth: Int = 3, cellNum: Int = 3) =
    ("|" + " " * cellWidth) * cellNum + "|" + eol
  // defines the grid and default size is 10x10 field
  def mesh(cellWidth: Int = 3, cellNum: Int = cellNumero) =
    (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(
      cellWidth,
      cellNum
    )
  // prints grid default size is 10x10
  println(mesh())
