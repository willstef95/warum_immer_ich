package de.htwg.se.wii
package controller

import model.Field
import util.Command
import util.Observable

class PutXCommand(pos: Int) extends Command[Field] {

  override def noStep(field: Field): Field = field

  override def doStep(field: Field): Field = field.putX(pos)

  override def undoStep(field: Field): Field = field.putO(pos)

  override def redoStep(field: Field): Field = field.putX(pos)

}
