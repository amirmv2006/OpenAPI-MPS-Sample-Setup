package ir.amv.snippets.openapisandbox.user

import ir.amv.os.snippets.generated.api.UserRoleApi
import ir.amv.os.snippets.generated.model.UserDto
import ir.amv.os.snippets.generated.model.UserRoleDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserRoleController(
    private val repo: UserRoleRepository
): UserRoleApi {
    override fun createUserRole(userId: UUID, userRoleDto: UserRoleDto): ResponseEntity<UUID> {
        return ResponseEntity.ok(
            repo.save()
        )
    }

    override fun deleteUserRole(userId: UUID?, id: UUID?): ResponseEntity<UserDto> {
        return super.deleteUserRole(userId, id)
    }

    override fun getUserRoles(userId: UUID?): ResponseEntity<MutableList<UserRoleDto>> {
        return super.getUserRoles(userId)
    }

    private fun UserRoleEntity.toDto() =
        UserRoleDto()
            .id(id)
            .name(name)

    private fun UserRoleDto.toEntity() =
        UserRoleEntity(
            id = id,
            name = name
        )
}
