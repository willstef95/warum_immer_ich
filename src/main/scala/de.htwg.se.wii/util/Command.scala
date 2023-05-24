package de.htwg.se.wii.util

import de.htwg.se.wii.controller.Controller

trait Command {
  def doStep(): Unit
  def undoStep(): Unit
  def redoStep(): Unit
}

class SetX(gewurfelt: Int, controller: Controller) extends Command {
  override def doStep(): Unit = {
    controller.putX(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat - 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
  }
  override def undoStep(): Unit = {
    controller.putO(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat + 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
  }
  override def redoStep(): Unit = {
    controller.putX(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat - 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
  }
}

class SetO(gewurfelt: Int, controller: Controller) extends Command {
  override def doStep(): Unit = {
    controller.putO(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat - 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
  }
  override def undoStep(): Unit = {
    controller.putX(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat + 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
  }
  override def redoStep(): Unit = {
    controller.putO(gewurfelt)
    println(
      s"Spieler ${controller.game.names(stat - 1)} hat: ${controller.pensdown(stat)} Stifte"
    )
  }
}
