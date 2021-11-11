package ir.amv.snippets.openapisandbox.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    @Id
    var id: UUID,
    var firstName: String?,
    var lastName: String?
)

interface UserRepo: JpaRepository<UserEntity, UUID>
