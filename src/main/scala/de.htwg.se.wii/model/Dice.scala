package de.htwg.se.wii.model

case class Dice(size: Int = 6):
  def roll(): Int = {
    val r = scala.util.Random
    r.nextInt(size)
  }
