package de.htwg.se.wii

import de.htwg.se.wii.controller.controllerComponent.Controller
import de.htwg.se.wii.controller.controllerComponent.ControllerInterface
import de.htwg.se.wii.model.MatrixComponent.{Matrix, MatrixInterface}
import de.htwg.se.wii.model.holes.HoleO
import de.htwg.se.wii.model.fileIoComponent.fileIoJsonImpl.JsonFileIo
import de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl.XmlFileIo
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.holes._
import com.google.inject.AbstractModule
import com.google.inject.name.Names

class WiiModule extends AbstractModule {
  val defaultHole: Hole = Hole(HoleO, 0)
  override def configure() = {

    val field = new Field(3, defaultHole)
    val fileIo = new JsonFileIo
    bind(classOf[ControllerInterface]).toInstance(
      new Controller(field, fileIo, 5)
    )
    bind(classOf[FieldInterface]).toInstance(
      new Field(3, defaultHole)
    )
    bind(classOf[MatrixInterface[Hole]])
      .toInstance(new Matrix(3, defaultHole))

  }
}
