package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.CartItem
import scala.collection.mutable.ListBuffer

@Singleton
class CartController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  private val cart = ListBuffer[CartItem]()

  def getAll = Action { Ok(Json.toJson(cart)) }

  def add = Action(parse.json) { request =>
    val item = request.body.as[CartItem]
    cart += item
    Created(Json.toJson(item))
  }

  def delete(id: Int) = Action {
    val index = cart.indexWhere(_.id == id)
    if (index != -1) { cart.remove(index); NoContent } else NotFound
  }
  
}