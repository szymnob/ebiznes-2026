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
}