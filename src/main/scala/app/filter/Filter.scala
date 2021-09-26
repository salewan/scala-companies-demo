package app.filter

sealed trait Filter

case class NameContainsFilter(test: String) extends Filter {
  def filter(name: String): Boolean = name.trim.toLowerCase().contains(test)
}

case object IsProfitableFilter extends Filter {
  def filter(isProfitable: Boolean): Boolean = isProfitable
}