package de.htwg.se.wii
package aview

import model.holes.*
import util.Observer
import util.Event
import scala.util.Random
import scala.annotation.switch
import scala.util.control.Breaks._
import scala.io.StdIn.readLine
import util.Stat
import controller.Controller
import scala.util.{Try, Success, Failure}
import controller.controllerComponent.ControllerInterface
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface

class TUI(controller: ControllerInterface) extends GameUI, Observer:
  controller.add(this)

  val eol = sys.props("line.separator")
  var processInputReturn = true
  val size = controller.field.size

  override def run(): Unit = {
    println("Los geht das Spiel")
    println("Name Spieler 1:")
    gameLoop()
  }

  def update(e: Event): Unit = {
    e match
      case Event.Roll => {
        if (controller.game.roll == 0) {
          println("Es wurde 0 gewurfelt das spielfeld bleibt gleich")
          println(controller.field.toString())
          printt()
        } else {
          println(s"Es wurde ${controller.game.roll} gewuerfelt" + eol)
          println(controller.field.toString())
          printt()
        }
      }
      case Event.Load => { println("Willkommen zurück!")
      printt()}
      case Event.Quit => print("quit")
      case Event.Finish => {
        println(s"Es wurde ${controller.game.roll} gewuerfelt" + eol)
        println(controller.field.toString())
        println(
          s"${controller.game.names(Stat.stat - 1)} hat das Spiel gewonnen!"
        )
        println("Auf Wiedersehen")
        processInputReturn = false
      }
      case Event.Start => printt()
  }

  def gameLoop(): Unit = {
    if (processInputReturn == true) {
      var input = readLine
      if (controller.game.names(0) == "Spieler1") {
        println("Name Spieler 2:")
        val player1 = input
        val player2 = readLine
        controller.init(player1, player2)
        input = readLine
      }

      printt()
      val xx = Try(
        processInput(input)
      )
      xx match
        case Failure(i) =>
          println("Falsche eingabe")
          printt()
          gameLoop()
        case Success(i) => {
          gameLoop()
        }
    }
  }

  def   printt(): Boolean = {
    println(s"${controller.game.names(0)} hat: ${controller.game.pens1} Stifte")
    println(
      s"${controller.game.names(1)} hat: ${controller.game.pens2} Stifte" + eol
    )
    println(s"Es ist ${controller.game.names(Stat.stat - 1)} ")
    println("'w' fuer Wuerfeln eingeben")
    true
  }

  def processInput(input: String): Boolean = {
    input match
      case "y" => controller.doAndPublish(controller.redo); true
      case "z" => controller.doAndPublish(controller.undo); true
      case "s" => controller.save; true
      case "l" => {
        controller.load
        printt()
        true
      }
      case "q" =>
        processInputReturn = false;
        false
      case "w" => {
        controller.round()
        true
      }

  }
