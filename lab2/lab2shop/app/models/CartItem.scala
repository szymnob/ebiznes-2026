package models
import play.api.libs.json._

case class CartItem(id: Int, productId: Int, quantity: Int)

object CartItem {
  implicit val format = Json.format[CartItem]
}