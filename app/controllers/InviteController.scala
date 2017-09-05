package controllers

import javax.inject._

import dispatch.Defaults._
import dispatch._
import models._
import org.json4s.JsonAST.JObject
import play.api.Logger
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class InviteController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val inviteForm = Form(
    mapping(
      "email" -> text
    )(InviteRequest.apply)(InviteRequest.unapply)
  )

  def invite = Action.async { implicit request =>
    (
      System.getenv("SLACK_API_TOKEN"),
      System.getenv("SLACK_TEAM")
    ) match {
      case (null | "", _) => {
        val result = InviteResponse(ok = false, Some("invalid_api_token"))
        Future.successful(BadRequest(Json.toJson(result)))
      }
      case (_, null | "") => {
        val result = InviteResponse(ok = false, Some("invalid_team"))
        Future.successful(BadRequest(Json.toJson(result)))
      }
      case (token, team) => {
        inviteForm.bindFromRequest.fold(
          _ => Future.successful(BadRequest),
          inviteData => {
            Logger.debug(s"team:$team email:${inviteData.email}")

            val req = url(s"https://$team.slack.com/api/users.admin.invite")
              .addQueryParameter("token", token)
              .addQueryParameter("email", inviteData.email)
              .addQueryParameter("set_active", "true")
              .POST

            for {
              res <- Http.default(req OK as.json4s.Json)
              result <- {
                val obj = res.asInstanceOf[JObject].values
                val ok = obj.get("ok").asInstanceOf[Option[Boolean]]
                val error = obj.get("error").asInstanceOf[Option[String]]
                val result = InviteResponse(ok.getOrElse(false), error)
                Future.successful(Ok(Json.toJson(result)))
              }
            } yield result
          }
        )
      }
    }
  }
}
