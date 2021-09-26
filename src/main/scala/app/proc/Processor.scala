package app.proc

import app.filter.Filterable

trait Processor[T <: Filterable] {

  def push(entity: T)
  def result: Seq[T]
}
