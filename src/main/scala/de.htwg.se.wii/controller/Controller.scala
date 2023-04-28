package de.htwg.se.wii
package controller

import model.Field
import model.Hole
import util.Observable
import model.Dice

case class Controller(var field: Field, size: Int) extends Observable:
  val dice = Dice((size * size))
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
