package de.htwg.se.wii
package controller

import model.Field
import model.holes.*
import util.Observable
import util.Event
import util.EventCases
import model.Dice
import model.Game
import scala.runtime.LazyVals.Names
import util.Command
import util.Stat
import java.util.Observer
import util.UndoManager

case class Controller(var field: Field, size: Int) extends Observable:
  val dice = Dice((size * size))
  var game = new Game(("Spieler1", "Spieler2"), 5, 5)
  val undoManager = new UndoManager[Field]

  override def toString = field.toString

  def doAndPublish(
      doThis: (Hole, Int) => Field,
      hole: Hole,
      stat: Int,
      roll: Int
  ) =
    field = doThis(hole, stat)
    notifyObservers(Event(roll, EventCases.Roll))

  def doAndPublish(doThis: => Field) =
    field = doThis
    notifyObservers(Event(0, EventCases.Undo))

  def zeroAndPublish(
      doThis: (Hole, Int) => Field,
      hole: Hole,
      stat: Int
  ) =
    field = doThis(hole, stat)
    notifyObservers(Event(0, EventCases.Zero))

  def undo: Field = undoManager.undoStep(field)
  def redo: Field = undoManager.redoStep(field)

  def putX(hole: Hole, stat: Int): Field =
    undoManager.doStep(field, PutXCommand(this, hole, stat))

  def putO(hole: Hole, stat: Int): Field =
    undoManager.doStep(field, PutOCommand(this, hole, stat))

  def get(pos: Int): HoleState = {
    val hole = field.get(pos)
    hole
  }

  def round = {
    val number = roll()
    wurf(number) match
      case None => roll0(0)
      case Some(roll) =>
        rollNot0(roll)
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

  def wurf(roll: Int): Option[Int] = {
    roll match
      case 0 => {
        None
      }
      case _ => {
        Some(roll)
      }
  }

  def roll0(roll: Int): Boolean = {
    zeroAndPublish(putX, Hole(HoleO, roll), Stat.stat)
    true
  }
  def rollNot0(roll: Int): Boolean = {

    get(roll) == HoleX match
      case true => {
        oSetzen(roll)
      }
      case false => {
        xSetzen(roll)
      }
    true
  }

  def oSetzen(roll: Int): Boolean = {
    doAndPublish(putO, Hole(HoleO, roll), Stat.stat, roll)
    true
  }

  def xSetzen(roll: Int): Boolean = {
    doAndPublish(putX, Hole(HoleO, roll), Stat.stat, roll)
    true
  }

  def isFinish(): Boolean = {
    var r = false
    if (game.pens1 == 0 || game.pens2 == 0) {
      r = true
    } else {
      Stat.stat match
        case 1 => {
          Stat.stat = 2
          false
        }
        case 2 => {
          Stat.stat = 1
          false
        }
      false
    }
    r
  }

case class NextStep(roll: Int):
  def get() = roll
