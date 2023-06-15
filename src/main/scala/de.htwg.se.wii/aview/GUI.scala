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
import scala.io.StdIn.readLine
import de.htwg.se.wii.model.holes.HoleState
import scala.swing.Reactions.Reaction
import de.htwg.se.wii.util.Stat
import controller.controllerComponent.ControllerInterface

class GUI(controller: ControllerInterface) extends Frame with Observer:
  minimumSize = new Dimension(400, 300)
  def cells = new CellPanel(controller.size, controller.size)
  controller.add(this)
  title = "Wii"
  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
      contents += new MenuItem(Action("Redo") {
        controller.doAndPublish(controller.redo)
      })
      contents += new MenuItem(Action("Undo") {
        controller.doAndPublish(controller.undo)
      })
    }
  }
  contents = init()
  pack()
  centerOnScreen()
  open()

  def init(): GridBagPanel = {
    val namefield1 = new TextField { columns = 5 }
    val namefield2 = new TextField { columns = 5 }

    val button2 = new Button("Update")

    val panel = new GridBagPanel {
      border = Swing.EmptyBorder(10)
      val c = new Constraints

      c.gridx = 1
      c.gridy = 0
      c.insets = new Insets(5, 5, 5, 5)
      layout(new Label("Willkommen zu Wii")) = c

      c.gridx = 0
      c.gridy = 1
      c.insets = new Insets(5, 5, 5, 5)
      layout(new Label("Name 1:")) = c

      c.gridx = 0
      c.gridy = 1
      c.insets = new Insets(5, 5, 5, 5)
      layout(new Label("Name 1:")) = c

      c.gridx = 1
      c.gridy = 1
      c.fill = GridBagPanel.Fill.Horizontal
      layout(namefield1) = c

      c.gridx = 0
      c.gridy = 2
      c.fill = GridBagPanel.Fill.None
      layout(new Label("Name 2:")) = c

      c.gridx = 1
      c.gridy = 2
      c.fill = GridBagPanel.Fill.Horizontal
      layout(namefield2) = c

      c.gridx = 2
      c.gridy = 2
      layout(button2) = c
    }

    button2.reactions += { case ButtonClicked(_) =>
      controller.init(namefield1.text, namefield2.text)
    }
    panel
  }

  def updateContents(update: Update): GridBagPanel = {
    val gridbag = new GridBagPanel {
      border = Swing.EmptyBorder(10)

      val c = new Constraints

      if (update == Update.Zero) {
        c.gridx = 1
        c.gridy = 0
        layout(new Label("Es wurde 0 gewurfelt, das Spielfeld bleibt gleich")) =
          c

        c.gridx = 1
        c.gridy = 2
        layout(
          new Label(s"Es ist ${controller.game.names(Stat.stat - 1)} ")
        ) = c

        c.gridx = 1
        c.gridy = 1
        layout(cells) = c

      } else if (update == Update.Start) {
        c.gridx = 1
        c.gridy = 0
        layout(new Label("Willkommen")) = c

        c.gridx = 1
        c.gridy = 1
        layout(
          new Label(
            "Es spielen: " + controller.game.names._1 + " und " + controller.game.names._2
          )
        ) = c

      } else if (update == Update.Finish) {
        c.gridx = 1
        c.gridy = 0
        layout(new Label("Juhhuuuuuu")) = c

        c.gridx = 1
        c.gridy = 1
        layout(
          new Label(
            s"${controller.game.names(Stat.stat - 1)} hat das Spiel gewonnen!"
          )
        ) = c

        c.gridx = 1
        c.gridy = 3
        layout(new finishButton()) = c

      } else {
        c.gridx = 1
        c.gridy = 0
        layout(new Label(s"Es wurde ${controller.game.roll} gewuerfelt")) = c

        c.gridx = 1
        c.gridy = 2
        layout(new Label(s"Es ist ${controller.game.names(Stat.stat - 1)} ")) =
          c

        c.gridx = 1
        c.gridy = 1
        layout(cells) = c
      }

      if (update == Update.Finish) {
        c.gridx = 1
        c.gridy = 3
        layout(new finishButton()) = c
      } else {
        c.gridx = 1
        c.gridy = 3
        layout(new dice()) = c
      }

      c.gridx = 0
      c.gridy = 4
      layout(
        new Label(
          s"${controller.game.names(0)}"
        )
      ) = c

      c.gridx = 0
      c.gridy = 5
      layout(
        new Label(
          ("| ") * controller.game.pens1
        )
      ) = c

      c.gridx = 2
      c.gridy = 4
      layout(
        new Label(
          s"${controller.game.names(1)}"
        )
      ) = c

      c.gridx = 2
      c.gridy = 5
      layout(
        new Label(
          ("| ") * controller.game.pens2
        )
      ) = c
    }
    gridbag
  }

  class dice() extends Button("Würfeln"):
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, props) => {
        controller.round()
      }
    }

  class finishButton() extends Button("Tschüs"):
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, props) => {
        sys.exit(0)
      }
    }

  enum Update:
    case Finish
    case Start
    case Zero
    case NotZero

  override def update(e: Event): Unit = e match
    case Event.Quit   => sys.exit(0)
    case Event.Finish => contents = updateContents(Update.Finish)
    case Event.Start  => contents = updateContents(Update.Start)
    case Event.Roll => {
      if (controller.game.roll == 0) {
        contents = updateContents(Update.Zero)
      } else {
        contents = updateContents(Update.NotZero)
      }
    }

  class CellPanel(x: Int, y: Int) extends GridPanel(x, y):
    (for (
      x <- 0 to x - 1;
      y <- 0 to y - 1
    ) yield (x, y, controller.field.matrix.cell(x, y))).foreach(t =>
      contents += new CellButton(t._1, t._2, t._3)
    )

  def button(stone: String) = new Button(stone)

  class CellButton(x: Int, y: Int, hole: HoleState)
      extends Button(hole.toString())
