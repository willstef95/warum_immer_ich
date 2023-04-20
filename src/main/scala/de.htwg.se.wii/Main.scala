package de.htwg.se.wii.model

import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine
import util.control.Breaks._

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

  var field = new Field(size, Hole.O)
  // print(field)

  println(s"Spieler1: $namePlayer1 beginnt")
  for (a <- 1 to 7) {
    print(field)

    println("Enter fuer Wuerfeln")
    val wurfel = readLine()
    val gewurfelt = dice.roll()
    println(s"Gewuerfelte Zahl: $gewurfelt" + eol)
    val x = gewurfelt % (size)
    val y =
      if (gewurfelt % (size - 1) == 0) gewurfelt / size
      else gewurfelt / size
    print(x, y)

<<<<<<< HEAD
    breakable {
      if (gewurfelt == 0) {
        print(field)
        break
      }
      println(gewurfelt + eol)
      field =
        if (field.get(x, y) == Hole.X) field.put(Hole.O, x, y)
        else field.put(Hole.X, x, y)

    }
  }
=======
  val gewurfelt = 6

  println(s"Gewuerfelte Zahl: $gewurfelt" + eol)

  val field2 = field.put(Hole.X, gewurfelt)
  print(field2)
  print(field2.get(6))
>>>>>>> 4e88c16224682e5a495ebdbc40012f495511cf62
