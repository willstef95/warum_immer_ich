package de.htwg.se.wii.model.fileIoComponent

import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.Game

import de.htwg.se.wii.util

trait FileIOInterface {

  def load: Game
  def save(game: Game, stat: Int): Unit
}
