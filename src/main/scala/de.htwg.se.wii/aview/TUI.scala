package de.htwg.se.wii
package aview

import controller.Controller
import model.Hole
import scala.io.StdIn.readLine
import util.Observer
import scala.util.Random
import scala.annotation.switch
import model.Dice
import scala.util.control.Breaks._

class TUI(controller: Controller, size: Int) extends Observer:
  controller.add(this)
  val dice = Dice((size * size))

  println("Name Spieler1 : ")
  val namePlayer1 = readLine
  println("Name Spieler2 : ")
  val namePlayer2 = readLine
  val eol = sys.props("line.separator")

  def run: Unit =
    println("Los geht das Spiel")
    gameLoop()

  override def update = println(controller.toString())

  def gameLoop(): Unit =
    println(controller.field.toString)
    for (a <- 1 to 10) {
      println("Enter fuer Wuerfeln")
      val wurfel = readLine()
      val gewurfelt = dice.roll()
      breakable {
        if (gewurfelt == 0) {
          println("Wurde 0 gewurfelt bleibt das spielfeld gleich")
          break
        }
        if (controller.get(gewurfelt) == Hole.X) {
          controller.put(Hole.O, gewurfelt)
          // conroller.lose(player)
        } else {
          controller.put(Hole.X, gewurfelt)
        }
        break
      }
    }
