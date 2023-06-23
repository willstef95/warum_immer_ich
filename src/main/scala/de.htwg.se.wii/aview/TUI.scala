package de.htwg.se.wii.aview

import de.htwg.se.wii.model.holes.*
import de.htwg.se.wii.util.Observer
import de.htwg.se.wii.util.Event
import de.htwg.se.wii.util.Stat
import de.htwg.se.wii.controller.controllerComponent.Controller
import de.htwg.se.wii.controller.controllerComponent.ControllerInterface
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import scala.util.{Try, Success, Failure}
import scala.util.Random
import scala.annotation.switch
import scala.util.control.Breaks._
import scala.io.StdIn.readLine

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
          printGameStat()
        } else {
          println(s"Es wurde ${controller.game.roll} gewuerfelt" + eol)
          println(controller.field.toString())
          printGameStat()
        }
      }
      case Event.Load => {
        println("Willkommen zurück!")
        println(controller.field.toString())
        printGameStat()
      }
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
      case Event.Start => printGameStat()
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
      printGameStat()
      val xx = Try(
        processInput(input)
      )
      xx match
        case Failure(i) =>
          println("Falsche eingabe")
          printGameStat()
          gameLoop()
        case Success(i) => {
          gameLoop()
        }
    }
  }

  def printGameStat(): Boolean = {
    println(s"${controller.game.names(0)} hat: ${controller.game.pens1} Stifte")
    println(s"${controller.game.names(1)} hat: ${controller.game.pens2} Stifte")
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
        controller.load;
        printGameStat()
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
