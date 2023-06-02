package de.htwg.se.wii
package aview

import swing._
import event._
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

import de.htwg.se.wii.aview.GameUI
import controller.Controller
import util.Observer
import scala.io.StdIn.readLine
import de.htwg.se.wii.model.holes.HoleState
import scala.swing.Reactions.Reaction

class GUI(controller: Controller) extends Frame with Observer:

  def cells = new CellPanel(3, 3)
  controller.add(this)
  title = "Wii"
  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }
  contents = updateContents
  pack()
  centerOnScreen()
  open()

  def updateContents = {
    new BorderPanel {
      add(
        new Label("Player: " + controller.game.names),
        BorderPanel.Position.North
      )
      add(cells, BorderPanel.Position.Center)
    }
  }

  override def update: Unit =
    contents = updateContents

  class CellPanel(x: Int, y: Int) extends GridPanel(x, y):
    (for (
      x <- 0 to 2;
      y <- 0 to 2
    ) yield (x, y, controller.field.matrix.cell(x, y))).foreach(t =>
      contents += new CellButton(t._1, t._2, t._3)
    )

  def button(stone: String) = new Button(stone)

  class CellButton(x: Int, y: Int, hole: HoleState)
      extends Button(hole.toString())
