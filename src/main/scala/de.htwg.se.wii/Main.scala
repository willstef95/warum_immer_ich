package de.htwg.se.wii

import aview.TUI
import aview.GUI
import controller.*
import model.FieldComponent.*
import model.MatrixComponent.*
import model.holes.*
import scala.util.Random
import scala.annotation.switch
import scala.io.StdIn.readLine
import de.htwg.se.wii.aview.{GameUI, TUI, GUI}
import com.google.inject.Guice
import controller.controllerComponent.ControllerInterface
import de.htwg.se.wii.WiiModule

object Wii {
  @main def run(): Unit =
    println("Herzlich Willkommen")
    // println(
    //   "Wie gross soll das Spielfeld sein? 2x2,3x3,4x4,5x5... Bitte geben Sie eine Zahl ein"
    // )
    // val size = readLine().toInt

    val injector = Guice.createInjector(new WiiModule)
    val controller = injector.getInstance(classOf[ControllerInterface])

    val gui = new GUI(controller)
    val tui: GameUI = TUI(controller)
    tui.run()
}
