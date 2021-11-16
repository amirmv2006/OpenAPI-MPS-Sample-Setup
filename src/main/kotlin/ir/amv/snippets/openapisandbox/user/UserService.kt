package ir.amv.snippets.openapisandbox.user

import ir.amv.snippets.openapisandbox.infra.EmailApi
import ir.amv.snippets.openapisandbox.infra.NotificationApi
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val repo: UserRepo,
    private val emailApi: EmailApi,
    private val notificationApi: NotificationApi
) {
    fun findById(id: UUID): UserEntity? =
        repo.findByIdOrNull(id)

    fun findAll(): List<UserEntity> =
        repo.findAll()

    fun UserEntity.registerUser(): UUID {
        requestActivation()
        emailApi.sendActivationLink(this)
        val saved = repo.save(this)
        notificationApi.notifyCreatedUser(saved)
        return saved.id
    }

    fun UUID.updateUser(newValue: UserEntity) {
        repo.findByIdOrNull(this)!!
            .also {
                it.updateValuesWith(newValue)
                repo.save(it)
            }
    }

    fun UUID.deleteUser() {
        repo.findByIdOrNull(this)!!
            .also {
                repo.delete(it)
            }
    }

}
