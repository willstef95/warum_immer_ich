package de.htwg.se.wii
package controller

import model.Field
import model.Hole
import util.Observable

case class Controller(var field: Field) extends Observable:
  override def toString(): String = field.toString
  def put(hole: Hole, pos: Int): Unit =
    field = field.put(hole, pos)
    notifyObservers
  def get(pos: Int): Hole =
    var hole = field.get(pos)
    hole
