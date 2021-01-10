package controllers

import javax.inject._
import models.Teacher
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.TeacherService


@Singleton
class TeacherController @Inject()(val service: TeacherService, val messagesApi: MessagesApi) extends Controller with I18nSupport {
  import play.api.libs.concurrent.Execution.Implicits.defaultContext

  val teacherForm = Form(
    mapping(
      "id" -> ignored[Option[Int]](None),
      "name" -> text(maxLength = 64),
      "email" -> email.verifying("Maximum length is 512", _.size <= 512),
      "phone" -> text.verifying("Bad phone number", {_.grouped(2).size == 6})
    )(Teacher.apply)(Teacher.unapply)
  )


  def getTeachers = Action.async {
    service.findAll().map { teachers =>
      Ok(views.html.teachers(teachers)(teacherForm))
    }
  }


  def AddTeacherPage() = Action.async {
    service.findAll().map { teachers =>
      Ok(views.html.AddTeacherPage(teachers)(teacherForm))
  }
  }
  
  def addTeacher = Action.async { implicit request =>
    teacherForm.bindFromRequest.fold(
      formWithErrors => {
        service.findAll().map { teachers =>
          BadRequest(views.html.AddTeacherPage(teachers)(formWithErrors))
        }
      },
      teacher => {
        service.insert(teacher).map { user =>
          Redirect(routes.TeacherController.getTeachers())
        } recoverWith {
          case _ => service.findAll().map { teachers =>
            BadRequest(views.html.teachers(teachers)(teacherForm))
          }
        }
      }
    )
  }
}
