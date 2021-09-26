package app.proc

import app.filter.{Filter, Filterable}

trait OrderedField {
  def ordered: Long
}

class TopProcessor[T <: Filterable with OrderedField](topN: Int, filters: Seq[Filter]) extends Processor[T] {
  private var top = Vector[T]()

  override def push(entity: T): Unit = {
    if (filters.forall(f => entity.filter(f))) {
      top = top.appended(entity).sortBy(- _.ordered).take(topN)
    }
  }

  override def result: Seq[T] = top
}
