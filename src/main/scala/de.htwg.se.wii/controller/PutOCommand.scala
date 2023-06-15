package de.htwg.se.wii
package controller

import model.FieldComponent.*
import model.MatrixComponent.*
import util.Command
import model.holes.Hole
import util.Stat

class PutOCommand(controller: Controller, hole: Hole, stat: Int)
    extends Command[FieldInterface] {

  override def noStep(field: FieldInterface): FieldInterface = field

  override def doStep(field: FieldInterface): FieldInterface =
    controller.pensup(stat)
    field.putO(hole.pos)

  override def undoStep(field: FieldInterface): FieldInterface =
    controller.pensdown(stat)
    field.putX(hole.pos)

  override def redoStep(field: FieldInterface): FieldInterface =
    if (stat == 2) {
      controller.pensup(2)
    } else {
      controller.pensup(1)
    }
    field.putO(hole.pos)
}
