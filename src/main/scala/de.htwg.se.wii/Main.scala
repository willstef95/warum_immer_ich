package de.htwg.se.wii.model

import scala.util.Random
import scala.annotation.switch

@main def wii(): Unit =
  println("Herzlich Willkommen zum Spiel wii")
  val dice = Dice(8)
  val eol = sys.props("line.separator")
  print("Gewuerfelte Zahl: " + dice.roll() + endl)

  val feld1 = new Field(3)
