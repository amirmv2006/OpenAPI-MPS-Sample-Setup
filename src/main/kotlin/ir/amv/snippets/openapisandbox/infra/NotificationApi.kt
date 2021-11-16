package ir.amv.snippets.openapisandbox.infra

import ir.amv.snippets.openapisandbox.user.UserEntity

interface NotificationApi {
    fun notifyCreatedUser(saved: UserEntity)
}
