package ir.amv.snippets.openapisandbox.user

import ir.amv.os.snippets.generated.api.UserRoleApi
import ir.amv.os.snippets.generated.model.UserRoleDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserRoleController(
    private val userRepo: UserRepo,
    private val repo: UserRoleRepository
) : UserRoleApi {
    override fun createUserRole(userId: UUID, userRoleDto: UserRoleDto): ResponseEntity<UUID> {
        return ResponseEntity.ok(
            repo.save(
                userRoleDto.toEntity(
                    userRepo.findByIdOrNull(userId)!!
                )
            ).id
        )
    }

    override fun deleteUserRole(userId: UUID?, id: UUID?): ResponseEntity<UserRoleDto> {
        return ResponseEntity.ok(
            repo.findByIdOrNull(id)!!
                .also { repo.delete(it) }
                .toDto()
        )
    }

    override fun getUserRoles(userId: UUID): ResponseEntity<List<UserRoleDto>> {
        return ResponseEntity.ok(
            repo.findByUserId(userId)
                .map { it.toDto() }
        )
    }

    override fun updateUserRole(userId: UUID, id: UUID, userRoleDto: UserRoleDto): ResponseEntity<UserRoleDto> {
        return ResponseEntity.ok(
            repo.findByIdOrNull(id)!!
                .also {
                    it.name = userRoleDto.name
                    repo.save(it)
                }
                .toDto()
        )
    }

    override fun findUserRoleById(userId: UUID, id: UUID): ResponseEntity<UserRoleDto> {
        return ResponseEntity.ok(
            repo.findByIdOrNull(id)
                .takeIf { it?.user?.id == userId }!!
                .toDto()
        )
    }

    private fun UserRoleEntity.toDto() =
        UserRoleDto()
            .id(id)
            .name(name)

    private fun UserRoleDto.toEntity(user: UserEntity) =
        UserRoleEntity(
            id = id,
            name = name,
            user = user
        )
}
