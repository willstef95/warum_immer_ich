package de.htwg.se.wii.model.fileIoComponent

import de.htwg.se.wii.model.Game

trait FileIOInterface {
  // def load: Game
  def save(game: Game, stat: Int): Unit
  def loadStat: Int
  def loadGame: Game
}
