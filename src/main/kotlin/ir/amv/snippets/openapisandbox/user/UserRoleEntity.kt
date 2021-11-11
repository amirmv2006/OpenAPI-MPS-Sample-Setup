package ir.amv.snippets.openapisandbox.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class UserRoleEntity(
    @Id
    var id: UUID,
    var name: String,
    @ManyToOne
    var user: UserEntity
)

interface UserRoleRepository : JpaRepository<UserRoleEntity, UUID> {
    fun findByUserId(userId: UUID): List<UserRoleEntity>
}
