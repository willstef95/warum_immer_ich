package de.htwg.se.wii
package controller

import model.Field
import util.Command
import model.holes.Hole
import controller.Controller
import util.Stat
import de.htwg.se.wii.model.Game

class PutOCommand(controller: Controller, hole: Hole, stat: Int)
    extends Command[Field] {

  override def noStep(field: Field): Field = field

  override def doStep(field: Field): Field =
    controller.pensup(stat)
    field.putO(hole.pos)

  override def undoStep(field: Field): Field =
    controller.pensdown(stat)
    field.putX(hole.pos)

  override def redoStep(field: Field): Field =
    if (stat == 2) {
      controller.pensup(2)
    } else {
      controller.pensup(1)
    }
    field.putO(hole.pos)
}
