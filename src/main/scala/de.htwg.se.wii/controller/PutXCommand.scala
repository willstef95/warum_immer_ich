package de.htwg.se.wii
package controller

import model.Field
import util.Command
import model.holes.Hole
import util.Stat

class PutXCommand(controller: Controller, hole: Hole) extends Command[Field] {

  override def noStep(field: Field): Field = field

  override def doStep(field: Field): Field =
    controller.pensup(Stat.stat)
    field.putX(hole.pos)

  override def undoStep(field: Field): Field =
    controller.pensup(Stat.stat)
    field.putO(hole.pos)

  override def redoStep(field: Field): Field =
    controller.pensup(Stat.stat)
    field.putX(hole.pos)

}
