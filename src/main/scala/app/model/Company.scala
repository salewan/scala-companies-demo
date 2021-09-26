package app.model

import app.filter.{Filter, Filterable, IsProfitableFilter, NameContainsFilter}
import app.proc.OrderedField

case class Company(companyName: String, revenue: Long, isProfitable: Boolean) extends Filterable with OrderedField {

  override def filter(filter: Filter): Boolean = {
    filter match {
      case x@NameContainsFilter(_) => x.filter(companyName)
      case x:IsProfitableFilter.type => x.filter(isProfitable)
    }
  }

  override def ordered: Long = revenue
}

object Company {
  def create(vector: Vector[String]): Company = Company(vector(0), vector(1).toLong, vector(2).toBoolean)
}