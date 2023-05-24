package de.htwg.se.wii
package aview

import controller.Controller
import model.holes.*
import util.Observer
import scala.util.Random
import scala.annotation.switch
import scala.util.control.Breaks._
import scala.io.StdIn.readLine

class TUI(controller: Controller, size: Int) extends GameUI, Observer:
  controller.add(this)

  val eol = sys.props("line.separator")

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

  var stat = 1

  def gameLoop(): Unit = {
    println(s"Es ist ${controller.game.names(stat - 1)}")
    println("Enter fuer Wuerfeln")
    val input = scala.io.StdIn.readLine
    processInput(input)

  }
  def processInput(input: String): Boolean = {
    input match
      case "q" =>
        false
      case _ => {
        val gewurfelt = controller.roll()
        gewurfelt match
          case 0 => {
            roll0(0)
          }
          case _ => {
            rollNot0(gewurfelt)
          }
        isFinish()
        true
      }
  }

  def roll0(n: Int): Boolean = {
    println("Es wurde 0 gewurfelt das spielfeld bleibt gleich")
    println(
      s"Spieler ${controller.game.names(stat - 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
    update
    true
  }

  def rollNot0(gewurfelt: Int): Boolean = {
    println(s"Es wurde ${gewurfelt} gewurfel")

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
    controller.putO(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat - 1)} hat: ${controller.pensup(stat)} Stifte"
    )
    true
  }

  def xSetzen(gewurfelt: Int): Boolean = {
    controller.putX(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat - 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
    true
  }

  def isFinish(): Boolean = {

    if (controller.game.pens1 == 0 || controller.game.pens2 == 0) {
      println(
        s"${controller.game.names(stat - 1)} hat das Spiel gewonnen!"
      )
    } else {
      stat match
        case 1 => {
          stat = 2
          gameLoop()
          true
        }
        case 2 => {
          stat = 1
          gameLoop()
          true
        }
      true
    }
    true
  }
