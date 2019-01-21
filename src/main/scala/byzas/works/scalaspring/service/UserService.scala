package byzas.works.scalaspring.service

import java.lang
import java.util.Optional

import byzas.works.scalaspring.model.User
import byzas.works.scalaspring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.{PostAuthorize, PreAuthorize}
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

//  @PreAuthorize("hasRole('admin')")
  def listUsers(): lang.Iterable[User] = userRepository.findAll

  @PreAuthorize("hasRole('user')")
  @PostAuthorize("returnObject.username==principal.username || hasRole('admin')")
  def getUser(id: Long): Optional[User] = userRepository.findById(id)

  @PreAuthorize("hasRole('admin')")
  def createUser(user: User): Long = {
    userRepository.save(user)
    user.id
  }
}
