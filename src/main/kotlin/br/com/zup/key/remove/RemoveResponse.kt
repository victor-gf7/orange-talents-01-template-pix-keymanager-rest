package br.com.zup.key.remove

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class RemoveResponse(
    @field:NotBlank val pixId: String,
    @field:NotBlank val clientId: String,
)
