package de.htwg.se.wii.model

import de.htwg.se.wii.model.FieldComponent.FieldInterface

case class Game(
    field: FieldInterface,
    names: (String, String),
    pens1: Int,
    pens2: Int,
    roll: Int) {

  def toXml() = <game>
    <field>
      {field.size.toString}
    </field> <name1>
      {names(0)}
    </name1> <name2>
      {names(1)}
    </name2> <pens1>
      {pens1}
    </pens1> <pens2>
      {pens2}
    </pens2> <roll>
      {roll}
    </roll>
  </game>
}