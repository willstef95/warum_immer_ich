package de.htwg.se.wii.holes

case class Hole(val state: HoleState) {
  def renderText(): String = state.renderText()
  // override def toString(): String = ???
}
