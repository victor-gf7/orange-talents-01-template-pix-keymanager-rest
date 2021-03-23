package br.com.zup.key.detail

import br.com.zup.KeyType
import io.micronaut.core.annotation.Introspected

@Introspected
data class PixKeyDetailResponse(
    val type: KeyType?,
    val key: String?,
    val account: AccountDetailResponse
)
