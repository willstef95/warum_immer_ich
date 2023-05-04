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
  var game = new Game(("Spieler1", "Spieler2"), 2, 2)

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
  def pensdown(spieler: Int): Unit =
    if (spieler == 1) {
      val x = game.pens1 - 1
      game = game.copy(pens1 = x)
      println(s"Spieler ${game.names(spieler - 1)} hat: ${game.pens1} Stifte")
    } else {
      val x = game.pens2 - 1
      game = game.copy(pens2 = x)
      println(s"Spieler ${game.names(spieler - 1)} hat: ${game.pens2} Stifte")
    }
  def pensup(spieler: Int): Unit =
    if (spieler == 1) {
      val x = game.pens1 + 1
      game = game.copy(pens1 = x)
      println(s"Spieler ${game.names(spieler - 1)} hat: ${game.pens1} Stifte")
    } else {
      val x = game.pens2 + 1
      game = game.copy(pens2 = x)
      println(s"Spieler ${game.names(spieler - 1)} hat: ${game.pens2} Stifte")
    }
  def init(names: (String, String)) = {
    game = game.copy(names)
  }
