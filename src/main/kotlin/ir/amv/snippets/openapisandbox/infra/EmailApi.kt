package ir.amv.snippets.openapisandbox.infra

import ir.amv.snippets.openapisandbox.user.UserEntity

interface EmailApi {
    fun sendActivationLink(userEntity: UserEntity)
}
