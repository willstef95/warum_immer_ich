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

case class GUI(controller: Controller, size: Int)
    extends SimpleSwingApplication,
      GameUI,
      Observer {

  controller.add(this)

  val eol = sys.props("line.separator")
  var processInputReturn = true

  override def run(): Unit = {
    println("Los geht das Spiel")
    controller.init(init())
    this.top
    gameLoop()
  }

  def init(): (String, String) = {
    println("Name Spieler 1:")
    val Player1 = readLine
    println("Name Spieler 2:")
    val Player2 = readLine
    (Player1, Player2)
  }

  def gameLoop(): Unit = {
    gameLoop()
  }
  override def update: Unit = ???

  def top = new MainFrame {
    title = "Hello world"

    contents = new FlowPanel {
      contents += new Label("Launch rainbows:")
      contents += new Button("Click me") {
        reactions += { case event.ButtonClicked(_) =>
          println("All the colours!")
        }
      }
    }

    pack()
    centerOnScreen()
    open()
  }
}
