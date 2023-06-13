package de.htwg.se.wii

import aview.TUI
import aview.GUI
import controller.*
import model.FieldComponent.*
import model.MatrixComponent.*
import model.holes.*
import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine
import de.htwg.se.wii.aview.{GameUI, TUI, GUI}

@main def run(): Unit =
  println("Herzlich Willkommen")
  // println(
  //   "Wie gross soll das Spielfeld sein? 2x2,3x3,4x4,5x5... Bitte geben Sie eine Zahl ein"
  // )
  // val size = readLine().toInt

  val size = 3
  val field = new Field()
  val controller = Controller(field, size)
  val gui = new GUI(controller)
  val tui: GameUI = TUI(controller, size)
  tui.run()
