package models

import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

case class Teacher(id: Option[Int], name: String, email: String, phone: String)

trait TeacherTable { this: HasDatabaseConfig[JdbcProfile] =>
  import dbConfig.driver.api._
  
  private[models] class Teachers(tag: Tag) extends Table[Teacher](tag, "TEACHERS") {
    // Columns
    def id = column[Int]("TEACHER_ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("TEACHER_NAME", O.Length(64))
    def email = column[String]("TEACHER_EMAIL", O.Length(512))
    def phone = column[String]("TEACHER_PHONE", O.Length(64))

    // Select
    def * = (id.?, name, email, phone) <> (Teacher.tupled, Teacher.unapply)
  }
  
  val teachers = TableQuery[Teachers]
}

