package de.htwg.se.wii.model.fileIoComponent

import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.Game

trait FileIOInterface {
  def save(game: Game, stat: Int): Unit
  def loadStat: Int
  def loadGame: Game
}
