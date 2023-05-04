package de.htwg.se.wii

import aview.TUI
import controller.Controller
import model.Field
import model.Matrix
import model.Hole
import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine

@main def run(): Unit =
  println("Hallo wii")
  println(
    "Wie gross soll das Spielfeld sein? 2x2,3x3,4x4,5x5... Bitte geben Sie eine Zahl ein"
  )

  val size = readLine().toInt
  val field = new Field(size, Hole.O)
  val controller = Controller(field, size)
  val tui = TUI(controller, size)
  tui.run
