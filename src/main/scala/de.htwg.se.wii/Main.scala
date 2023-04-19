package de.htwg.se.wii.model

import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine

@main def wii(): Unit =
  val eol = sys.props("line.separator")

  println("Herzlich Willkommen zum Spiel wii")

  println("Bitte Name von Spieler 1 eingeben")
  val namePlayer1 = readLine()
  println("Bitte Name von Spieler 2 eingeben")
  val namePlayer2 = readLine()
  println(
    "Wie gross soll das Spielfeld sein? 2x2,3x3,4x4,5x5... Bitte geben Sie eine Zahl ein"
  )
  val size = readLine().toInt

  val dice = Dice((size * size))

  println(s"Spieler1: , $namePlayer1 beginnt")

  val field = new Field(size, Hole.O)
  print(field)
  field.replaceCell(2, 2, Hole.O)

  // println(s"Gewuerfelte Zahl: " + dice.roll() + eol)
  // print(field.get(2, 2))
  // field.put(Hole.O, 2, 2)
  // print(field.get(2, 2))
  // print(field)
  // field.replaceCell(2, 2, Hole.O)

// val field = new Field(3, Hole.X)
// field.def
