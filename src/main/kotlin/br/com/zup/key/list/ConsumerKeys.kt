package br.com.zup.key.list

import io.micronaut.core.annotation.Introspected

@Introspected
data class ConsumerKeys(
    val clientId: String?,
    val pixKey: List<ConsumerListKeysResponse>
)
