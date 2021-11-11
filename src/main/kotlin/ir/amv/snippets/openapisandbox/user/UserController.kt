package ir.amv.snippets.openapisandbox.user

import ir.amv.os.snippets.generated.api.UserApi
import ir.amv.os.snippets.generated.model.UserDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserController(
    private val repo: UserRepo
) : UserApi {
    override fun createUser(userDto: UserDto): ResponseEntity<UUID> {
        return ResponseEntity.ok(
            repo.save(
                userDto.toEntity(UUID.randomUUID())
            ).id
        )
    }

    override fun getUserById(id: UUID): ResponseEntity<UserDto> {
        return ResponseEntity.ok(
            repo.findByIdOrNull(id)!!.toDto()
        )
    }

    override fun listUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(
            repo.findAll()
                .map { it.toDto() }
        )
    }

    override fun updateUser(id: UUID, userDto: UserDto): ResponseEntity<UserDto> {
        return ResponseEntity.ok(
            repo.findByIdOrNull(id)!!
                .run {
                    firstName = userDto.firstName
                    lastName = userDto.lastName
                    repo.save(this)
                }
                .toDto()
        )
    }

    override fun deleteUserById(id: UUID): ResponseEntity<UserDto> {
        return ResponseEntity.ok(
            repo.findByIdOrNull(id)!!
                .also {
                    repo.delete(it)
                }
                .toDto()
        )
    }

    private fun UserDto.toEntity(id: UUID? = null): UserEntity =
        UserEntity(
            id = id ?: this.id,
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            deathDate = deathDate
        )

    private fun UserEntity.toDto(): UserDto =
        UserDto()
            .id(id)
            .firstName(firstName)
            .lastName(lastName)
            .birthDate(birthDate)
            .deathDate(deathDate)
}
