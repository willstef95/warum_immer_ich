package de.htwg.se.wii
package controller

import model.Field
import model.Hole
import util.Observable
import model.Dice
import model.Game
import scala.runtime.LazyVals.Names

case class Controller(var field: Field, size: Int) extends Observable:
  val dice = Dice((size * size))
  var game = new Game(("Spieler1", "Spieler2"), 5, 5)

  override def toString(): String = field.toString
  def put(hole: Hole, pos: Int): Unit =
    field = field.put(hole, pos)
    notifyObservers
  def get(pos: Int): Hole =
    val hole = field.get(pos)
    hole
  def roll(): Int =
    val roll = dice.roll()
    roll
  def pensdown(spieler: Int): Int =
    if (spieler == 1) {
      val x = game.pens1 - 1
      game = game.copy(pens1 = x)
      x
    } else {
      val x = game.pens2 - 1
      game = game.copy(pens2 = x)
      x
    }

  def pensup(spieler: Int): Int =
    if (spieler == 1) {
      val x = game.pens1 + 1
      game = game.copy(pens1 = x)
      x
    } else {
      val x = game.pens2 + 1
      game = game.copy(pens2 = x)
      x
    }
  def init(names: (String, String)) = {
    game = game.copy(names)
  }
