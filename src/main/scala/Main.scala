import app.filter.{FilterConfig, FilterRegistry, IsProfitableFilter, IsProfitableFilterCreator, NameContainsFilterCreator}
import app.model.Company
import app.proc.TopProcessor
import play.api.libs.json.Json

import scala.io.Source

object Main extends App {
  val FILTERS_FILE = "filters.json"
  val DATA_ROWS_FILE = "companies.csv"
  val TOP_NUM = 3
  val filterSo = Source.fromFile(FILTERS_FILE)
  val dataSo = Source.fromFile(DATA_ROWS_FILE)
  FilterRegistry.register("NameContains", NameContainsFilterCreator)
  FilterRegistry.register("IsProfitable", IsProfitableFilterCreator)

  val filterConfigs = Json.parse(filterSo.getLines().reduce(_ + _)).as[Seq[FilterConfig]]
  val filters = filterConfigs.map(_.findFilter)
  if (filters.exists(_.isEmpty)) {
    Logger.error("Some of filter creators needs to be added to registry")
  }

  val proc = new TopProcessor[Company](TOP_NUM, filters.filter(_.isDefined).map(_.get))
  dataSo.getLines().foreach { line =>
    val company = Company.create(line.split(",").map(_.trim).toVector)
    proc.push(company)
  }

  proc.result.foreach(println)

  filterSo.close()
  dataSo.close()
}