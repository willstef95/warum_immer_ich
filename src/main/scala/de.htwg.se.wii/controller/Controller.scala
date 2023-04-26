package de.htwg.se.wii

package controller

import model.Field
import model.Hole
import util.Observable

case class Controller(var field: Field) extends Observable:
  def put(hole: Hole, x: Int): Unit =
    field = field.put(hole, x)
    notifyObservers
  override def toString: String = field.toString
