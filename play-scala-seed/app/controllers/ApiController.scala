package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import respository.DataRepository

@Singleton
class ApiController @Inject()(cc: ControllerComponents, dataRepository: DataRepository) extends AbstractController(cc) {
  def ping = Action { implicit request =>
    Ok("Hello, World!")
  }

  def getPost(postId: Int) = Action { implicit request =>
    dataRepository.getPost(postId) map { post =>
      Ok(Json.toJson(post))
    } getOrElse NotFound
  }

  def getComment(postId: Int) = Action { implicit request =>
    Ok(Json.toJson(dataRepository.getComments(postId)))
  }
}
