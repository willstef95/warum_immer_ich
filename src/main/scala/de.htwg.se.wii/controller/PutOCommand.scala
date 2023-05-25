package de.htwg.se.wii
package controller

import model.Field
import util.Command
import model.holes.Hole
import controller.Controller

class PutOCommand(hole: Hole, player: Int) extends Command[Field] {

  override def noStep(field: Field): Field = field

  override def doStep(field: Field): Field =
    controller.Controller.field.putO(hole.pos)

  override def undoStep(field: Field): Field = field.putX(hole.pos)

  override def redoStep(field: Field): Field = field.putO(hole.pos)
}
