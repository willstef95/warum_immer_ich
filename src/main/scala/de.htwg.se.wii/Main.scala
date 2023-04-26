package de.htwg.se.wii

import aview.TUI
import controller.Controller
import model.Field
import model.Matrix
import model.Hole
import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine
// import util.control.Breaks._

@main def run(): Unit =

  println("Hallo wii")
  val field = new Field(3, Hole.O)
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run

  // val eol = sys.props("line.separator")

  // println("Herzlich Willkommen zum Spiel wii")

  // println("Bitte Name von Spieler 1 eingeben")
  // val namePlayer1 = readLine()
  // println("Bitte Name von Spieler 2 eingeben")
  // val namePlayer2 = readLine()
  // println(
  //   "Wie gross soll das Spielfeld sein? 2x2,3x3,4x4,5x5... Bitte geben Sie eine Zahl ein"
  // )
  // val size = readLine().toInt

  // val dice = Dice((size * size))

  // var field = new Field(size, Hole.O)
  // var state = true
  // var treffer = true

  // for (a <- 1 to 10) {
  //   if (state) {
  //     println(s"Spieler1: $namePlayer1 darf Wuerfeln")
  //     print(field)
  //     println("Enter fuer Wuerfeln")
  //     val wurfel = readLine()
  //     val gewurfelt = dice.roll()
  //     println(s"$namePlayer1 wurfelte: $gewurfelt $eol")

  //     breakable {
  //       if (gewurfelt == 0) {
  //         // print(field)
  //         break
  //       }
  //       field =
  //         if (field.get(gewurfelt) == Hole.X) field.put(Hole.O, gewurfelt)
  //         else field.put(Hole.X, gewurfelt)

  //       if (field.get(gewurfelt) == Hole.O) {
  //         state = false
  //       }
  //     }
  //   } else {
  //     println(s"Spieler2: $namePlayer2 darf Wuerfeln")
  //     print(field)
  //     println("Enter fuer Wuerfeln")
  //     val wurfel = readLine()
  //     val gewurfelt = dice.roll()
  //     println(s"$namePlayer2 wurfelte: $gewurfelt $eol")

  //     breakable {
  //       if (gewurfelt == 0) {
  //         print(field)
  //         break
  //       }
  //       field =
  //         if (field.get(gewurfelt) == Hole.X) field.put(Hole.O, gewurfelt)
  //         else field.put(Hole.X, gewurfelt)
  //       if (field.get(gewurfelt) == Hole.O) {
  //         state = true
  //       }
  //     }
  //   }
  //  }
