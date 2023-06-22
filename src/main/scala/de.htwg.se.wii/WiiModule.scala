package de.htwg.se.wii

import controller.controllerComponent.ControllerInterface
import controller.controllerComponent._
import model.holes
import model.FieldComponent.{Field, FieldInterface}
import de.htwg.se.wii.model.holes._
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.wii.controller.Controller
import de.htwg.se.wii.model.MatrixComponent.{Matrix, MatrixInterface}
import de.htwg.se.wii.model.holes.HoleO
import de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.wii.model.fileIoComponent.FileIOInterface

class WiiModule extends AbstractModule {

  val defaultSize: Int = 10
  val penscount: Int = 20

  val defaultHole: Hole = Hole(HoleO, 0)

  override def configure() = {

    val field = new Field(defaultSize, defaultHole)
    bind(classOf[ControllerInterface]).toInstance(
      new Controller(field, penscount)
    )
    bind(classOf[FieldInterface]).toInstance(
      new Field(defaultSize, defaultHole)
    )
    bind(classOf[FileIOInterface]).toInstance(
      new FileIO()
    )
    bind(classOf[MatrixInterface[Hole]])
      .toInstance(new Matrix(defaultSize, defaultHole))

  }
}
