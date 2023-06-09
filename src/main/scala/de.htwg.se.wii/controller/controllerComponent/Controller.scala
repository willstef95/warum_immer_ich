package de.htwg.se.wii.controller.controllerComponent

import de.htwg.se.wii.controller.PutOCommand
import de.htwg.se.wii.controller.PutXCommand
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.MatrixComponent.MatrixInterface
import de.htwg.se.wii.model.holes.*
import de.htwg.se.wii.util.Event
import de.htwg.se.wii.util.Observable
import de.htwg.se.wii.util.UndoManager
import de.htwg.se.wii.util.Command
import de.htwg.se.wii.util.Stat
import de.htwg.se.wii.model.Dice
import de.htwg.se.wii.model.Game
import scala.runtime.LazyVals.Names
import java.util.Observer
import de.htwg.se.wii.controller.controllerComponent.ControllerInterface
import com.google.inject.Inject
import com.google.inject.Guice
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface

case class Controller @Inject() (
    var fieldr: FieldInterface,
    val fileIo: FileIOInterface,
    penscount: Int
) extends ControllerInterface()
    with Observable {

  var field = fieldr

  val size = field.size
  val dice = Dice((size * size))
  var game = new Game(field, ("Spieler1", "Spieler2"), penscount, penscount, 0)
  val undoManager = new UndoManager[FieldInterface]

  override def toString = field.toString

  def doAndPublish(
      doThis: (Hole, Int) => FieldInterface,
      hole: Hole,
      stat: Int
  ) =
    field = doThis(hole, stat)
    game = game.copy(field = field)
    if (isFinish() == true) {
      notifyObservers(Event.Finish)
    } else {
      notifyObservers(Event.Roll)
    }

  def doAndPublish(doThis: => FieldInterface) =
    field = doThis
    game = game.copy(field = field)
    if (isFinish() == true) {
      notifyObservers(Event.Finish)
    } else {
      notifyObservers(Event.Roll)
    }

  def undo: FieldInterface = undoManager.undoStep(game.field)
  def redo: FieldInterface = undoManager.redoStep(game.field)

  def putX(hole: Hole, stat: Int): FieldInterface =
    undoManager.doStep(game.field, PutXCommand(this, hole, stat))

  def putO(hole: Hole, stat: Int): FieldInterface =
    undoManager.doStep(game.field, PutOCommand(this, hole, stat))

  def get(pos: Int): HoleState = {
    val hole = game.field.get(pos)
    hole
  }

  def round() = {
    val number = roll()
    wurf(number) match
      case None => roll0(0)
      case Some(roll) =>
        rollNot0(roll)
  }

  def roll(): Int =
    val roll = dice.roll()
    game = game.copy(roll = roll)
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
    notifyObservers(Event.Start)
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
    doAndPublish(putX, Hole(HoleO, roll), Stat.stat)
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

  def oSetzen(gewurfelt: Int): Boolean = {
    doAndPublish(putO, Hole(HoleO, gewurfelt), Stat.stat)
    true
  }

  def xSetzen(gewurfelt: Int): Boolean = {
    doAndPublish(putX, Hole(HoleO, gewurfelt), Stat.stat)
    true
  }

  def save = {
    fileIo.save(game, Stat.stat)
  }

  def load = {
    Stat.stat = fileIo.loadStat
    game = fileIo.loadGame
    println(game.field.toString())
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
}
