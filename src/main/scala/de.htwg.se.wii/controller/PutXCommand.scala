package de.htwg.se.wii
package controller

import model.FieldComponent.*
import model.MatrixComponent.*

import util.Command
import model.holes.Hole
import util.Stat

class PutXCommand(controller: Controller, hole: Hole, stat: Int)
    extends Command[FieldInterface] {

  override def noStep(field: FieldInterface): FieldInterface = field

  override def doStep(field: FieldInterface): FieldInterface =
    controller.pensdown(stat)
    if (hole.pos == 0) {
      field.putO(0)
    } else {
      field.putX(hole.pos)
    }

  override def undoStep(field: FieldInterface): FieldInterface =
    controller.pensup(stat)
    field.putO(hole.pos)

  override def redoStep(field: FieldInterface): FieldInterface =
    if (stat == 2) {
      controller.pensdown(2)
    } else {
      controller.pensdown(1)
    }
    field.putX(hole.pos)

}
