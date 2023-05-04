package de.htwg.se.wii
package controller

import model.Field
import model.Hole
import util.Observable
import model.Dice
import model.Game

case class Controller(var field: Field, size: Int) extends Observable:
  val dice = Dice((size * size))
  var game = new Game(5, 5)
  override def toString(): String = field.toString
  def put(hole: Hole, pos: Int): Unit =
    field = field.put(hole, pos)
    notifyObservers
  def get(pos: Int): Hole =
    var hole = field.get(pos)
    hole
  def roll(): Int =
    val roll = dice.roll()
    roll
  def pensdown(): Unit =
    println(s"Spieler hat: ${game.pensPlayer1} Stifte")
    val x = game.pensPlayer1 - 1
    game = game.copy(pensPlayer1 = x)
