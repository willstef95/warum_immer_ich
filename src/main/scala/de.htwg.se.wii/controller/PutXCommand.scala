package de.htwg.se.wii
package controller

import model.Field
import util.Command
import model.holes.Hole
import util.Stat

class PutXCommand(controller: Controller, hole: Hole, stat: Int)
    extends Command[Field] {

  override def noStep(field: Field): Field = field

  override def doStep(field: Field): Field =
    controller.pensdown(stat)
    if (hole.pos == 0) {
      field.putO(0)
    } else {
      field.putX(hole.pos)
    }

  override def undoStep(field: Field): Field =
    if (stat == 1) {
      controller.pensup(0)
    } else {
      controller.pensup(1)
    }
    field.putO(hole.pos)

  override def redoStep(field: Field): Field =
    if (stat == 1) {
      controller.pensdown(0)
    } else {
      controller.pensdown(1)
    }
    field.putX(hole.pos)

}
