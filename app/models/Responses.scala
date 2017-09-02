package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class InviteResponse(ok: Boolean, error: Option[String])

object InviteResponse {
  implicit val inviteResponseWrites = (
    (__ \ "ok").write[Boolean] ~
      (__ \ "error").write[Option[String]]
    ) (unlift(InviteResponse.unapply))
}