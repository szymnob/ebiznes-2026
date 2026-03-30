package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Category
import scala.collection.mutable.ListBuffer

@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  private val categories = ListBuffer(Category(1, "Elektronika"), Category(2, "Dom"))

  def getAll = Action { Ok(Json.toJson(categories)) }
  
  def getById(id: Int) = Action {
    categories.find(_.id == id).map(c => Ok(Json.toJson(c))).getOrElse(NotFound)
  }

  def add = Action(parse.json) { request =>
    val category = request.body.as[Category]
    categories += category
    Created(Json.toJson(category))
  }

  def update(id: Int) = Action(parse.json) { request =>
    val data = request.body.as[Category]
    val index = categories.indexWhere(_.id == id)
    if (index != -1) {
      categories.update(index, data)
      Ok(Json.toJson(data))
    } else NotFound
  }

  def delete(id: Int) = Action {
    val index = categories.indexWhere(_.id == id)
    if (index != -1) { categories.remove(index); NoContent } else NotFound
  }
}