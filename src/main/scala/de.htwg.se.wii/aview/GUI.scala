package de.htwg.se.wii
package aview

import model.holes.*
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
import util.Event
import util.EventCases
import scala.io.StdIn.readLine
import de.htwg.se.wii.model.holes.HoleState
import scala.swing.Reactions.Reaction
import de.htwg.se.wii.util.Stat
import java.awt.peer.FramePeer

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
  contents = updateContents(0)
  pack()
  centerOnScreen()
  open()

  def updateContents(stat: Int) = {
    new BorderPanel {
      add(
        new Label("Player: " + controller.game.names),
        BorderPanel.Position.North
      )
      add(cells, BorderPanel.Position.Center)
      add(new dice(), BorderPanel.Position.South)
    }
  }

  class dice() extends Button("Würfeln"):
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, props) => {
        controller.round
      }
    }

  def update(e: Event): Unit = e match
    case Event(roll, EventCases.Quit) =>
    case Event(roll, EventCases.Roll) => contents = updateContents(0)
    case Event(roll, EventCases.Zero) =>
    case Event(roll, EventCases.Undo) =>

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
