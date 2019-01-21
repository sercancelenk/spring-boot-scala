package byzas.works.scalaspring.controller

import byzas.works.scalaspring.model.User
import byzas.works.scalaspring.service.UserService
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import java.lang
import java.util.Optional

import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.{PostAuthorize, PreAuthorize};

@RestController
@RequestMapping(path = Array("/api"))
class UserController(@Autowired val userService: UserService, @Autowired val dataSource: DataSource) {

  @GetMapping(path = Array("/users"))
  def getAllUsers(): lang.Iterable[User] = {
    userService.listUsers()
  }

  @GetMapping(path = Array("/user/{id}"))
  def getUser(@PathVariable id: Long): Optional[User] = {
    userService.getUser(id)
  }

  @PostMapping(path = Array("/user"))
  def createUser(@RequestBody user: User): ResponseEntity[Long] = {
    val id = userService.createUser(user)
    new ResponseEntity(id, new HttpHeaders, HttpStatus.CREATED)
  }





}
