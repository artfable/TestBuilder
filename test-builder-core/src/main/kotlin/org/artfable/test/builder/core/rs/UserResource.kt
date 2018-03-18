package org.artfable.test.builder.core.rs

import org.artfable.test.builder.core.model.AppUser
import org.artfable.test.builder.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

/**
 * @author artfable
 * 26.12.17
 */
@RestController
@RequestMapping(path = ["/api/users"])
class UserResource {

    @Autowired private lateinit var userService: UserService;

    @RequestMapping(path = ["/current"], method = [RequestMethod.GET])
    fun getCurrentUser(): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.currentUser)
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = [RequestMethod.GET])
    fun getAllUsers(): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.allUsers)
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = [RequestMethod.POST])
    fun createNewUser(@RequestBody user: AppUser): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.createNewUser(user))
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = ["/{id}"], method = [RequestMethod.PUT])
    fun updateUser(@PathVariable("id") id: Long, @RequestBody user: AppUser): ResponseEntity<Any> {
        if (id != user.id) {
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity.ok(userService.updateUser(user))
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteUser(@PathVariable("id") id: Long): ResponseEntity<Any> {
        userService.deleteUser(id)
        return ResponseEntity.ok().build()
    }
}