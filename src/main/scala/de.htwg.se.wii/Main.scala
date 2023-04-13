package de.htwg.se.wii.model

import scala.util.Random
import scala.annotation.switch

@main def wii(): Unit =
  println("Herzlich Willkommen zum Spiel wii")
  val dice = Dice(8)
  print(dice.roll())

/*
val dice_bottom = "‾‾‾‾‾‾‾‾‾‾"
val dice_top = "__________"
val dice_2m = "| ▢   ▢ |"
val dice_1_m = "|   ▢    |"
val dice_1_l = "|▢       |"
val dice_1_r = "|       ▢|"
val space = "|        |"

def dice_1 =
  dice_top + eol + space + eol + dice_1_m + eol + space + eol + dice_bottom + eol

def dice_2 =
  dice_top + eol + dice_1_r + eol + space + eol + dice_1_l + eol + dice_bottom + eol

def dice_3 =
  dice_top + eol + dice_1_r + eol + dice_1_m + eol + dice_1_l + eol + dice_bottom + eol

def dice_4 =
  dice_top + eol + dice_2m + eol + space + eol + dice_2m + eol + dice_bottom + eol

def dice_5 =
  dice_top + eol + dice_2m + eol + dice_1_m + eol + dice_2m + eol + dice_bottom + eol

def dice_6 =
  dice_top + eol + dice_2m + eol + dice_2m + eol + dice_2m + eol + dice_bottom + eol



def eol = sys.props("line.separator")
def PgrenzeO_U(b: Int = 2) = "---------" * b + eol
def PgrenzeL_R(b: Int = 2) = "|       |"
def PgrenzeL_RZ(b: Int = 20) = "|   " + zahl() + "   |"
def PgrenzeL_RO(b: Int = 2) = "|   O   |"
def feldLeer(b: Int = 2) = PgrenzeL_R() * b + eol
def feldO(b: Int = 2) = PgrenzeL_RO() * b + eol
def feldX(b: Int = 2) = PgrenzeL_RZ() * b + eol
def zahl(b: Int = 0) = "x"
def feld(b: Int = 2) = feldLeer(b) + feldO(b) + feldX(b) + feldLeer(b)
def ausgaben(b: Int = 2) =
  ((PgrenzeO_U(b) + feld(b)) * b + PgrenzeO_U(b))
 */
