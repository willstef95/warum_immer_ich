package de.htwg.se.wii

import de.htwg.se.wii.model.holes._
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.wii.controller.controllerComponent.Controller
import de.htwg.se.wii.controller.controllerComponent.ControllerInterface
import de.htwg.se.wii.model.MatrixComponent.{Matrix, MatrixInterface}
import de.htwg.se.wii.model.holes.HoleO
import de.htwg.se.wii.model.fileIoComponent.fileIoJsonImpl.JsonFileIo
import de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl.XmlFileIo
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.FieldComponent.FieldInterface

class WiiModule extends AbstractModule {

  val defaultSize: Int = 3
  val penscount: Int = 5

  val defaultHole: Hole = Hole(HoleO, 0)

  override def configure() = {

    val field = new Field(defaultSize, defaultHole)
    val fileIo = new JsonFileIo
    bind(classOf[ControllerInterface]).toInstance(
      new Controller(field, fileIo, penscount)
    )
    bind(classOf[FieldInterface]).toInstance(
      new Field(defaultSize, defaultHole)
    )
    bind(classOf[MatrixInterface[Hole]])
      .toInstance(new Matrix(defaultSize, defaultHole))

  }
}
