package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Product
import scala.collection.mutable.ListBuffer

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
    
    // In-memory list of products
    private val products = ListBuffer(
    Product(1, "Laptop", 2999.99),
    Product(2, "Myszka", 49.00)
  )


  def getAll = Action { Ok(Json.toJson(products)) }

  def getById(id: Int) = Action {
    products.find(_.id == id).map(p => Ok(Json.toJson(p))).getOrElse(NotFound)
  }

  def add = Action(parse.json) { request =>
    val product = request.body.as[Product]
    products += product
    Created(Json.toJson(product))
  }

  def update(id: Int) = Action(parse.json) { request =>
    val productData = request.body.as[Product]
    val index = products.indexWhere(_.id == id)
    if (index != -1) {
      products.update(index, productData)
      Ok(Json.toJson(productData))
    } else NotFound
  }

  def delete(id: Int) = Action {
    val index = products.indexWhere(_.id == id)
    if (index != -1) {
      products.remove(index)
      NoContent
    } else NotFound
  }
}