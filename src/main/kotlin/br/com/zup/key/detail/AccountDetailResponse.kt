package br.com.zup.key.detail

import br.com.zup.AccountType
import io.micronaut.core.annotation.Introspected

@Introspected
data class AccountDetailResponse(
    val type: AccountType?,
    val institution: String?,
    val cardholderName: String?,
    val cardholderCpf: String?,
    val agency: String?,
    val accountNumber: String?
)
