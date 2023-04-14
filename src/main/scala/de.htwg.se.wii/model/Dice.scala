package de.htwg.se.wii.model

case class Dice(size: Int = 6):
  val r = scala.util.Random
  def roll(): Int = {
    r.nextInt(size)
  }
