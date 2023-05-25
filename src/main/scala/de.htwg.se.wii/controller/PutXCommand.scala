package de.htwg.se.wii
package controller

import model.Field
import util.Command
import model.holes.Hole

class PutXCommand(hole: Hole) extends Command[Field] {

  override def noStep(field: Field): Field = field

  override def doStep(field: Field): Field = field.putX(hole.pos)

  override def undoStep(field: Field): Field = field.putO(hole.pos)

  override def redoStep(field: Field): Field = field.putX(hole.pos)

}
