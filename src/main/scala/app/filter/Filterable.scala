package app.filter

trait Filterable {
  def filter(filter: Filter): Boolean
}
