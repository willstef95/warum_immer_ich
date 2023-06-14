package de.htwg.se.wii
package wii

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import model.FieldComponent.FieldInterface
import model.FieldComponent.Field
import model.MatrixComponent.MatrixInterface
import model.MatrixComponent.Matrix

class WiiModule extends AbstractModule with ScalaModule {

  val defaultSize: Int = 3

  override def configure(): Unit = {
    // bind[MatrixInterface].to[Matrix]
    bind[FieldInterface].to[Field]
  }
}
