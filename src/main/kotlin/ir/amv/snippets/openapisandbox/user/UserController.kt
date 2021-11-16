package ir.amv.snippets.openapisandbox.user

import ir.amv.os.snippets.generated.api.UserApi
import ir.amv.os.snippets.generated.model.UserDto
import org.springframework.core.convert.ConversionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserController(
    private val userService: UserService,
    private val conversionService: ConversionService
) : UserApi {

    private inline fun <reified T> Any.convertTo(): T =
        conversionService.convert(this, T::class.java)!!

    override fun createUser(userDto: UserDto): ResponseEntity<UUID> {
        val userId = with(userService) {
            userDto.convertTo<UserEntity>().registerUser()
        }
        return ResponseEntity.ok(userId)
    }

    override fun getUserById(id: UUID): ResponseEntity<UserDto> {
        return ResponseEntity.ok(
            userService.findById(id)!!.convertTo()
        )
    }

    override fun listUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(
            userService.findAll()
                .map { it.convertTo() }
        )
    }

    override fun updateUser(id: UUID, userDto: UserDto): ResponseEntity<Void> {
        with(userService) {
            id.updateUser(userDto.convertTo<UserEntity>())
        }
        return ResponseEntity.ok(null)
    }

    override fun deleteUserById(id: UUID): ResponseEntity<Void> {
        with(userService) {
            id.deleteUser()
        }
        return ResponseEntity.ok(null)
    }
}
