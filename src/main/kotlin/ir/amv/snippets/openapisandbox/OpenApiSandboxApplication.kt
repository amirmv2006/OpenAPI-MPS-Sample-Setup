package ir.amv.snippets.openapisandbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OpenApiSandboxApplication

fun main(args: Array<String>) {
    runApplication<OpenApiSandboxApplication>(*args)
}
