package de.htwg.se.wii
package aview

import controller.Controller
import model.Hole
import util.Observer
import scala.util.Random
import scala.annotation.switch
import scala.util.control.Breaks._
import scala.io.StdIn.readLine

class TUI(controller: Controller, size: Int) extends Observer:
  controller.add(this)

  val eol = sys.props("line.separator")

  def run: Unit = {
    println("Los geht das Spiel")
    controller.init(init())
    gameLoop()
  }

  def init(): (String, String) = {
    println("Name Spieler 1:")
    val namePlayer1 = readLine
    println("Name Spieler 2:")
    val namePlayer2 = readLine
    (namePlayer1, namePlayer2)
  }

  override def update = println(controller.toString())

  def gameLoop(): Unit =
    println(controller.pensdown())
    println("Enter fuer Wuerfeln")
    // println(s"${controller.game.names(0)}")
    val input = readLine()
    input match
      case "q" =>
      case _ => {
        val gewurfelt = controller.roll()
        breakable {

          if (gewurfelt == 0) {
            println("Wurde 0 gewurfelt bleibt das spielfeld gleich")
            println(controller.field.toString)
            break
          }
          if (controller.get(gewurfelt) == Hole.X) {
            controller.put(Hole.O, gewurfelt)
          } else {
            controller.put(Hole.X, gewurfelt)
          }
          break
        }
        gameLoop()
      }
