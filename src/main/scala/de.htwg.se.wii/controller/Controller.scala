package de.htwg.se.wii
package controller

import model.Field
import model.holes.*
import util.Observable
import model.Dice
import model.Game
import scala.runtime.LazyVals.Names
import util.Command
import java.util.Observer
import util.UndoManager
import model.SavePoint

case class Controller(var field: Field, size: Int) extends Observable:
  val dice = Dice((size * size))
  var game = new Game(("Spieler1", "Spieler2"), 5, 5)
  val undoManager = new UndoManager[Field]

  override def toString = field.toString

  def doAndPublish(doThis: Hole => Field, hole: Hole) =
    field = doThis(hole)
    notifyObservers()

  def doAndPublish(doThis: => Field) =
    field = doThis
    notifyObservers()

  def undo: Field = undoManager.undoStep(field)
  def redo: Field = undoManager.redoStep(field)

  def putX(hole: Hole): Field =
    undoManager.doStep(field, PutXCommand(this, hole))

  def putO(hole: Hole): Field =
    undoManager.doStep(field, PutOCommand(this, hole))

  def get(pos: Int): HoleState = {
    val hole = field.get(pos)
    hole
  }

  def roll(): Int =
    val roll = dice.roll()
    roll

  def pensdown(spieler: Int): Int =
    if (spieler == 1) {
      game = game.copy(pens1 = game.pens1 - 1)
      game.pens1
    } else {
      game = game.copy(pens2 = game.pens2 - 1)
      game.pens2
    }

  def pensup(spieler: Int): Int =
    if (spieler == 1) {
      game = game.copy(pens1 = game.pens1 + 1)
      game.pens1
    } else {
      game = game.copy(pens2 = game.pens2 + 1)
      game.pens2
    }

  def init(names: (String, String)) = {
    game = game.copy(names = names)
  }
