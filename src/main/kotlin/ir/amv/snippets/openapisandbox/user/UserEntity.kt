package ir.amv.snippets.openapisandbox.user

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity {
    @Id
    lateinit var id: UUID
    var firstName: String? = null
    var lastName: String? = null
    lateinit var status: Status
    var activationCode: UUID? = null
    lateinit var birthDate: LocalDate
    lateinit var deathDate: OffsetDateTime

    fun requestActivation() {
        this.activationCode = UUID.randomUUID()
        status = Status.PENDING
    }

    fun updateValuesWith(newValue: UserEntity) {
        assert(this.id == newValue.id)
        this.firstName = newValue.firstName
        this.lastName = newValue.lastName
        this.status = newValue.status
        this.activationCode = newValue.activationCode
        this.birthDate = newValue.birthDate
        this.deathDate = newValue.deathDate
    }
}

enum class Status {
    DISABLED,
    PENDING,
    ENABLED
}

interface UserRepo : JpaRepository<UserEntity, UUID>
