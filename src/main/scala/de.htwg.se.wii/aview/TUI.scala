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
    val Player1 = readLine
    println("Name Spieler 2:")
    val Player2 = readLine
    (Player1, Player2)
  }

  override def update = println(controller.toString())

  var stat = 1

  def gameLoop(): Unit = {

    println(s"Es ist ${controller.game.names(stat - 1)}")
    println("Enter fuer Wuerfeln")
    val input = readLine()
    input match
      case "q" =>
      case _ => {
        val gewurfelt = controller.roll()
        breakable {
          if (gewurfelt == 0) {
            println("Wurde 0 gewurfelt bleibt das spielfeld gleich")
            println(controller.pensdown(stat))
            update
            break
          }
          if (controller.get(gewurfelt) == Hole.X) {
            controller.put(Hole.O, gewurfelt)
            val pen = controller.pensup(stat)
            println(
              s"Spieler ${controller.game.names(stat - 1)} hat: ${pen} Stifte"
            )

          } else {
            controller.put(Hole.X, gewurfelt)
            val pen = controller.pensdown(stat)
            println(
              s"Spieler ${controller.game.names(stat - 1)} hat: ${pen} Stifte"
            )
          }
          break
        }
        breakable {
          if (controller.game.pens1 == 0 || controller.game.pens2 == 0)
            break

          if (stat == 1) { stat = 2 }
          else { stat = 1 }

          gameLoop()
        }

      }
  }
