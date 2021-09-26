package app.filter

import play.api.libs.json.Json

case class FilterConfig(filterType: String, text: Option[String]) {
  def findFilter: Option[Filter] = FilterRegistry.findFilter(filterType).map(_.getFilter(this))
}

object FilterConfig {
  implicit val format = Json.format[FilterConfig]
}