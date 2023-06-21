package de.htwg.se.wii.model

import de.htwg.se.wii.model.FieldComponent.FieldInterface

case class Game(
    field: FieldInterface,
    names: (String, String),
    pens1: Int,
    pens2: Int,
    roll: Int
):
  def toXml() = <game><field>{field}</field><names>{names}</names><pens1>{
    pens1
  }</pens1><pens2>{pens2}</pens2><roll>{roll}</roll></game>
