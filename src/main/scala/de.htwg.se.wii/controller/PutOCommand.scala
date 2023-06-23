package de.htwg.se.wii.controller

import de.htwg.se.wii.controller.controllerComponent.Controller
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.MatrixComponent.MatrixInterface
import de.htwg.se.wii.util.Command
import de.htwg.se.wii.util.Stat
import de.htwg.se.wii.model.holes.Hole

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
