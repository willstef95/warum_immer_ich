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
      case None    => "Das war leidr keine Zahl :)"
    }

  val dice = Dice(8)
  val eol = sys.props("line.separator")
  print("Gewuerfelte Zahl: " + dice.roll() + eol)
  print(field)
  print(field.get(2, 2))
  field.put(Hole.O, 2, 2)
  print(field.get(2, 2))
  print(field)
  field.replaceCell(2, 2, Hole.O)

val field = new Field(3, Hole.X)
// field.replaceCell(2, 2, Hole.O)
// field.def
