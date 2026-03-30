package models
import play.api.libs.json._

case class Product(id: Int, name: String, price: Double)

object Product {
  implicit val format = Json.format[Product]
}