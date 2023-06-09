package de.htwg.se.wii.util

trait Observer:
  def update(e: Event): Unit

trait Observable:
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer): Unit = subscribers = subscribers :+ s
  def remove(s: Observer): Unit = subscribers =
    subscribers.filterNot(o => o == s)
  def notifyObservers(e: Event): Unit = {
    subscribers.foreach(o => o.update(e))
  }

enum Event:
  case Quit
  case Roll
  case Start
  case Finish
  case Load
