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
import de.htwg.se.wii.model.fileIoComponent.fileIoJsonImpl.FileIO as JsonFileIo
import de.htwg.se.wii.model.fileIoComponent.fileIoXmlImpl.FileIO as XMLFileIo

class WiiModule extends AbstractModule {

  val defaultSize: Int = 3
  val penscount: Int = 5
  val defaultHole: Hole = Hole(HoleO, 0)

  override def configure() = {

    val field = new Field(defaultSize, defaultHole)
    val fileIo = new XMLFileIo
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
