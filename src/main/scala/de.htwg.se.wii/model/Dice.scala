package de.htwg.se.wii.model

case class Dice(size: Int = 6):
<<<<<<< HEAD
=======
  val r = scala.util.Random
>>>>>>> 08b9690ca4c47d0883e4bfe32deecd6e0e6efd55
  def roll(): Int = {
    r.nextInt(size)
  }
