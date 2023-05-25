package de.htwg.se.wii.model.holes

case class Hole(val state: HoleState, pos: Int) {
  def renderText(): String = state.renderText()
  // override def toString(): String = ???
}
