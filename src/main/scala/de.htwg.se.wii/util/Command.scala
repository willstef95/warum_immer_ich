package de.htwg.se.wii.util

import de.htwg.se.wii.controller.Controller
import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.util.Stat

trait Command {
  def doStep(): Unit
  def undoStep(): Unit
  def redoStep(): Unit
}

class SetX(gewurfelt: Int, controller: Controller) extends Command {
  override def doStep(): Unit = {
    controller.putX(gewurfelt)
    println(
      s"Spieler ${controller.game.names(Stat.stat - 1)} hat: ${controller
          .pensdown(Stat.stat)} Stifte"
    )
  }
  override def undoStep(): Unit = {
    controller.putO(gewurfelt)
    println(
      s"Spieler ${controller.game.names(Stat.stat + 1)} hat: ${controller
          .pensdown(Stat.stat)} Stifte"
    )
  }
  override def redoStep(): Unit = {
    controller.putX(gewurfelt)
    println(
      s"Spieler ${controller.game.names(Stat.stat - 1)} hat: ${controller
          .pensdown(Stat.stat)} Stifte"
    )
  }
}

class SetO(gewurfelt: Int, controller: Controller) extends Command {
  override def doStep(): Unit = {
    controller.putO(gewurfelt)
    println(
      s"Spieler ${controller.game.names(Stat.stat - 1)} hat: ${controller
          .pensdown(Stat.stat)} Stifte"
    )
  }
  override def undoStep(): Unit = {
    controller.putX(gewurfelt)
    println(
      s"Spieler ${controller.game.names(Stat.stat + 1)} hat: ${controller
          .pensdown(Stat.stat)} Stifte"
    )
  }
  override def redoStep(): Unit = {
    controller.putO(gewurfelt)
    println(
      s"Spieler ${controller.game.names(Stat.stat - 1)} hat: ${controller
          .pensdown(Stat.stat)} Stifte"
    )
  }
}
