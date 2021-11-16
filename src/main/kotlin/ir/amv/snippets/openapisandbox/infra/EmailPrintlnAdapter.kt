package ir.amv.snippets.openapisandbox.infra

import ir.amv.snippets.openapisandbox.user.UserEntity

class EmailPrintlnAdapter : EmailApi {
    override fun sendActivationLink(userEntity: UserEntity) {
        println("Hey ${userEntity.firstName} ${userEntity.lastName}, your activation code is ${userEntity.activationCode}")
    }
}
