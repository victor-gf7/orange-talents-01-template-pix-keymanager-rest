package br.com.zup.key.detail

import io.micronaut.core.annotation.Introspected

@Introspected
data class DetailKeyResponse(
    val pixId: String?,
    val clientId: String?,
    val pixKey: PixKeyDetailResponse
)
