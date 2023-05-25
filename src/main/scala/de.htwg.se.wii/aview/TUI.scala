package de.htwg.se.wii
package aview

import controller.Controller
import model.holes.*
import util.Observer
import scala.util.Random
import scala.annotation.switch
import scala.util.control.Breaks._
import scala.io.StdIn.readLine
import util.Stat
import controller.Controller
import scala.util.{Try, Success, Failure}
import de.htwg.se.wii.model.SavePoint

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

    println(s"${controller.game.names(0)} hat: ${controller.game.pens1} Stifte")
    println(
      s"${controller.game.names(1)} hat: ${controller.game.pens2} Stifte" + eol
    )
    println(s"Es ist ${controller.game.names(Stat.stat - 1)} ")
    println("'w' fuer Wuerfeln eingeben")

    val input = scala.io.StdIn.readLine
    val xx = Try(
      processInput(input)
    )
    xx match
      case Failure(i) =>
        println("Falsche eingabe")
        gameLoop()
      case Success(i) => {
        if (processInputReturn == true & isFinish() == true) {
          gameLoop()
        } else {
          println("Auf Wiedersehen")
        }
      }

  }
  def processInput(input: String): Boolean = {
    input match
      case "y" => controller.doAndPublish(controller.redo); true
      case "z" => controller.doAndPublish(controller.undo); true
      case "q" =>
        processInputReturn = false;
        false
      case "w" => {
        val gewurfelt = controller.roll()
        println(s"Es wurde ${gewurfelt} gewuerfelt")
        wurf(gewurfelt) match
          case None         => roll0(0)
          case Some(erfolg) => rollNot0(erfolg)

        true
      }
  }
  def wurf(input: Int): Option[Int] = {
    input match
      case 0 => {
        None
      }
      case _ => {
        Some(input)
      }
  }

  def roll0(n: Int): Boolean = {
    println("Es wurde 0 gewurfelt das spielfeld bleibt gleich")
    controller.doAndPublish(controller.putX, Hole(HoleO, n))

    // update
    true
  }

  def rollNot0(gewurfelt: Int): Boolean = {

    controller.get(gewurfelt) == HoleX match
      case true => {
        oSetzen(gewurfelt)
      }
      case false => {
        xSetzen(gewurfelt)
      }
    true
  }

  def oSetzen(gewurfelt: Int): Boolean = {
    controller.doAndPublish(controller.putO, Hole(HoleO, gewurfelt))
    // println(
    //   s"Spieler ${controller.game.names(Stat.stat - 1)} hat: ${controller.pensup(Stat.stat)} Stifte"
    // )
    true
  }

  def xSetzen(gewurfelt: Int): Boolean = {
    controller.doAndPublish(controller.putX, Hole(HoleO, gewurfelt))
    // println(
    //   s"Spieler ${controller.game.names(Stat.stat - 1)} hat: ${controller
    //       .pensdown(Stat.stat)} Stifte"
    // )
    true
  }

  def isFinish(): Boolean = {
    var r = true
    if (controller.game.pens1 == 0 | controller.game.pens2 == 0) {
      println(
        s"${controller.game.names(Stat.stat - 1)} hat das Spiel gewonnen!"
      )
      r = false

    } else {
      Stat.stat match
        case 1 => {
          Stat.stat = 2
          true
        }
        case 2 => {
          Stat.stat = 1
          true
        }
      true
    }
    r
  }
