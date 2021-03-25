package br.com.zup.key.list

import br.com.zup.AccountType
import br.com.zup.KeyType
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class ConsumerListKeysResponse(
    val pixId: String,
    val type: KeyType,
    val key: String,
    val accountType: AccountType,
    val registeredAt: String  //Utilizando string apenas para visualização
)
