package de.htwg.se.wii

import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.aview.GUI
import de.htwg.se.wii.controller.controllerComponent.ControllerInterface
import de.htwg.se.wii.controller.controllerComponent.Controller
import de.htwg.se.wii.model.FieldComponent.FieldInterface
import de.htwg.se.wii.model.FieldComponent.Field
import de.htwg.se.wii.model.MatrixComponent.MatrixInterface
import de.htwg.se.wii.model.MatrixComponent.Matrix
import de.htwg.se.wii.model.holes.*
import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine
import de.htwg.se.wii.aview.GUI
import de.htwg.se.wii.aview.TUI
import de.htwg.se.wii.aview.GameUI

import com.google.inject.Guice
import de.htwg.se.wii.WiiModule

object Wii {
  @main def run(): Unit =
    println("Herzlich Willkommen")

    val injector = Guice.createInjector(new WiiModule)
    val controller = injector.getInstance(classOf[ControllerInterface])

    val gui = new GUI(controller)
    val tui: GameUI = TUI(controller)
    tui.run()
}
