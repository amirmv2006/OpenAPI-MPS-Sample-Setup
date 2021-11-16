package ir.amv.snippets.openapisandbox.infra

import org.springframework.stereotype.Component

@Component
class AllPrintlnAdapter :
    EmailApi by EmailPrintlnAdapter(),
    NotificationApi by NotificationPrintlnAdapter()
