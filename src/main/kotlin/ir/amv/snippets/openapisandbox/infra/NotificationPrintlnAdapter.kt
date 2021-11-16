package ir.amv.snippets.openapisandbox.infra

import ir.amv.snippets.openapisandbox.user.UserEntity

class NotificationPrintlnAdapter : NotificationApi {
    override fun notifyCreatedUser(saved: UserEntity) {
        println("User $saved was created!")
    }
}
