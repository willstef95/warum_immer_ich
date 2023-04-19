package de.htwg.se.wii.model

import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine

@main def wii(): Unit =
  println("Herzlich Willkommen zum Spiel wii")
  println("Wie viele Spieler spielen mit ?")
  val playerNumb = readLine().toIntOption
  val playerNumbOut =
    playerNumb match {
      case Some(i) => "OK Es spielen " + i + " Spieler mit."
      case None    => "Das war leider keine Zahl :)"
    }

  val dice = Dice(8)
  val eol = sys.props("line.separator")
  print("Gewuerfelte Zahl: " + dice.roll() + eol)
<<<<<<< HEAD
  print(field)
  print(field.get(2, 2))
  field.put(Hole.O, 2, 2)
  print(field.get(2, 2))
  print(field)
  field.replaceCell(2, 2, Hole.O)
=======
>>>>>>> 08b9690ca4c47d0883e4bfe32deecd6e0e6efd55

val field = new Field(3, Hole.X)
// field.replaceCell(2, 2, Hole.O)
// field.def
