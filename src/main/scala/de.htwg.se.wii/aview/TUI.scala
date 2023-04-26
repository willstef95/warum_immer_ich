package de.htwg.se.wii
package aview

import controller.Controller
import model.Hole
import scala.io.StdIn.readLine
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)

  override def update: Unit = ???

  def run: Unit =
    println(controller.field.toString)
    println("Los gehts1")
