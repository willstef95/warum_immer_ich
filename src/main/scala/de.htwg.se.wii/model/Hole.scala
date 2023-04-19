package de.htwg.se.wii.model

enum Hole(stringRepresentation: String):
  override def toString = stringRepresentation
  case X extends Hole("X")
  case O extends Hole("O")
