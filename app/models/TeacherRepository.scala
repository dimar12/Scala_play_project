package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

@Singleton
class TeacherRepository @Inject()(val provider: DatabaseConfigProvider) extends HasDatabaseConfig[JdbcProfile] with TeacherTable {
  val dbConfig = provider.get[JdbcProfile]
  
  import dbConfig.driver.api._

  import scala.concurrent.ExecutionContext.Implicits.global
  
  def findAll() = db.run(teachers.result)
  
  def insert(teacher: Teacher) = db
    .run(teachers returning teachers.map(_.id) += teacher)
    .map(id => teacher.copy(id = Some(id)))

}
