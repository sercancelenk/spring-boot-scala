package byzas.works.scalaspring.repository

import byzas.works.scalaspring.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait UserRepository extends CrudRepository[User, Long] {
  def findUserByUsername(username: String): User
}
