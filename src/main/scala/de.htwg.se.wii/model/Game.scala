package de.htwg.se.wii.model

import de.htwg.se.wii.model.FieldComponent.FieldInterface

case class Game(
    field: FieldInterface,
    names: (String, String),
    pens1: Int,
    pens2: Int,
    roll: Int
)
