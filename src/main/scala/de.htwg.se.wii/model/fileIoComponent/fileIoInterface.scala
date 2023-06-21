package de.htwg.se.wii.model.fileIoComponent

import de.htwg.se.wii.model.FieldComponent.FieldInterface

trait FileIOInterface {

  def load: FieldInterface
  def save(field: FieldInterface): Unit

}
