package app.filter

import scala.collection.mutable

object FilterRegistry {

  private val all = mutable.HashMap[String, FilterCreator]()

  def register(name: String, filter: FilterCreator): Unit = {
    all.put(name, filter)
  }

  def findFilter(name: String): Option[FilterCreator] = all.get(name)
}
