package br.com.zup.key.remove

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class RemoveRequest(
    @field:NotBlank val clientId: String?,
)
