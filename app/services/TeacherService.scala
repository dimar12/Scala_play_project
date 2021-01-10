package services

import akka.actor.ActorSystem
import javax.inject.{Inject, Singleton}
import models._

import scala.concurrent.Future

@Singleton
class TeacherService @Inject()(val repository: TeacherRepository, system: ActorSystem) {
  
  def findAll(): Future[Seq[Teacher]] = repository.findAll
  def insert(teacher: Teacher): Future[Teacher] = repository
    .insert(teacher)
}