package ir.amv.snippets.openapisandbox.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserRoleEntity(
    @Id
    var id: UUID,
    var name: String
)

interface UserRoleRepository: JpaRepository<UserRoleEntity, UUID>
