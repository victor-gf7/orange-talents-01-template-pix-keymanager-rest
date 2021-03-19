package br.com.zup.key.register

import br.com.zup.AccountType
import br.com.zup.KeyType
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NewKeyRequest(
    @field:NotBlank val keyType: KeyType?,
    @field:Size(max = 77) val keyValue: String?,
    @field:NotBlank val accountType: AccountType?
)
