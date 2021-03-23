package br.com.zup.key.detail

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class DetailKeyRequest(
    @field:NotBlank val clientId: String?,
    @field:NotBlank val pixId: String?
)
