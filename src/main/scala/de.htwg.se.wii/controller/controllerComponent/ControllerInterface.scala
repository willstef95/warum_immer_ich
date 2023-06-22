package de.htwg.se.wii
package controller.controllerComponent

import de.htwg.se.wii.model.FieldComponent.FieldInterface
import model.holes._
import util.{Event, Observable, UndoManager}

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}
import de.htwg.se.wii.model.Dice
import de.htwg.se.wii.model.Game

trait ControllerInterface extends Observable {

  var game: Game
  val dice: Dice
  val undoManager: UndoManager[FieldInterface]
  val size: Int
  var field: FieldInterface

  def doAndPublish(
      doThis: (Hole, Int) => FieldInterface,
      hole: Hole,
      stat: Int
  ): Unit
  def doAndPublish(doThis: => FieldInterface): Unit
  def putX(hole: Hole, stat: Int): FieldInterface
  def putO(hole: Hole, stat: Int): FieldInterface
  def get(pos: Int): HoleState
  def round(): Unit
  def roll(): Int
  def pensdown(spieler: Int): Int
  def pensup(spieler: Int): Int
  def init(names: (String, String)): Unit
  def wurf(roll: Int): Option[Int]
  def roll0(roll: Int): Boolean
  def rollNot0(roll: Int): Boolean
  def oSetzen(gewurfelt: Int): Boolean
  def xSetzen(gewurfelt: Int): Boolean
  def isFinish(): Boolean
  def save: Unit
  def load: Unit

  def undo: FieldInterface
  def redo: FieldInterface
  override def toString: String
}
