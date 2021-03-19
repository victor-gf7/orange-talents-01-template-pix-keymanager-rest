package br.com.zup.key

import io.micronaut.core.annotation.Introspected

@Introspected
data class PixKeyResponse(val clientId: String?, val pixId: String?)