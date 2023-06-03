package de.htwg.se.wii
package aview

import model.holes.*
import util.Observer
import scala.util.Random
import scala.annotation.switch
import scala.util.control.Breaks._
import scala.io.StdIn.readLine
import util.Stat
import controller.Controller
import controller.NextStep
import scala.util.{Try, Success, Failure}

class TUI(controller: Controller, size: Int) extends GameUI, Observer:
  controller.add(this)

  val eol = sys.props("line.separator")
  var processInputReturn = true

  override def run(): Unit = {
    println("Los geht das Spiel")
    controller.init(init())
    gameLoop()
  }

  def init(): (String, String) = {
    println("Name Spieler 1:")
    val Player1 = readLine
    println("Name Spieler 2:")
    val Player2 = readLine
    (Player1, Player2)
  }

  override def update = println(controller.field.toString())

  def gameLoop(): Unit = {
    printt()
    val xx = Try(
      processInput(scala.io.StdIn.readLine)
    )
    xx match
      case Failure(i) =>
        println("Falsche eingabe")
        gameLoop()
      case Success(i) => {
        if (processInputReturn == true & controller.isFinish() == true) {
          gameLoop()
        } else {
          println("Auf Wiedersehen")
        }
      }
  }

  def printt(): Boolean = {
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
      case "q" =>
        processInputReturn = false;
        false
      case "w" => {
        val result = controller.round()
        val l = result.get()
        l match
          case 0 =>
            println("Es wurde 0 gewurfelt das spielfeld bleibt gleich")
            true
          case _ =>
            println(s"Es wurde ${result.get()} gewuerfelt")
            true

      }
  }
