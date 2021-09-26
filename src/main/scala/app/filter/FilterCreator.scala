package app.filter

sealed trait FilterCreator {
  def getFilter(filterConfig: FilterConfig): Filter
}

case object NameContainsFilterCreator extends FilterCreator {
  def getFilter(filterConfig: FilterConfig): Filter = NameContainsFilter(filterConfig.text.get)

}

case object IsProfitableFilterCreator extends FilterCreator {
  def getFilter(filterConfig: FilterConfig): Filter = IsProfitableFilter
}