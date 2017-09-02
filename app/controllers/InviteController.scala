package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class InviteController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def invite = Action {
    Ok(views.html.index())
  }
}
