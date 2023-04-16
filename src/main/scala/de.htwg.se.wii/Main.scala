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

  val feld1 = new Field(3)
