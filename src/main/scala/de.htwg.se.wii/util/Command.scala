package de.htwg.se.wii.util

import de.htwg.se.wii.controller.Controller
import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.util.Stat

trait Command[T]:
  def noStep(t: T): T
  def doStep(t: T): T
  def undoStep(t: T): T
  def redoStep(t: T): T

class UndoManager[T]:
  private var undoStack: List[Command[T]] = Nil
  private var redoStack: List[Command[T]] = Nil
  def doStep(t: T, command: Command[T]): T =
    undoStack = command :: undoStack
    command.doStep(t)
  def undoStep(t: T): T =
    undoStack match {
      case Nil => t
      case head :: stack => {
        val result = head.undoStep(t)
        undoStack = stack
        redoStack = head :: redoStack
        result
      }
    }
  def redoStep(t: T): T =
    redoStack match {
      case Nil => t
      case head :: stack => {
        val result = head.redoStep(t)
        redoStack = stack
        undoStack = head :: undoStack
        result
      }
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
