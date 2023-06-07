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
      case Event.Quit  =>
      case Event.Start => printt()
  }

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
        val finish = controller.isFinish()
        if (finish == true) {
          println(
            s"${controller.game.names(Stat.stat - 1)} hat das Spiel gewonnen!"
          )
        }
        if (processInputReturn == true & finish == false) {
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
        controller.round()
        true
      }
  }
