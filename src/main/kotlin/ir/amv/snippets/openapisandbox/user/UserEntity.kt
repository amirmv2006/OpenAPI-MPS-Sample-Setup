package ir.amv.snippets.openapisandbox.user

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    @Id
    var id: UUID,
    var firstName: String?,
    var lastName: String?,
    var birthDate: LocalDate,
    var deathDate: OffsetDateTime
)

interface UserRepo : JpaRepository<UserEntity, UUID>
