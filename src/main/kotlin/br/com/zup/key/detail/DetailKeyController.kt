package br.com.zup.key.detail

import br.com.zup.KeyDetailRequest
import br.com.zup.KeyManagerDetailGRPCServiceGrpc
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/api/v1/key")
class DetailKeyController(
    @Inject val grpcClient: KeyManagerDetailGRPCServiceGrpc.KeyManagerDetailGRPCServiceBlockingStub
) {

    @Get("/detail/{pixId}")
    fun detailPixKey(
        @QueryValue clientId: String,
        @PathVariable pixId: String
    ): HttpResponse<DetailKeyResponse> {

        val requestGrpc = KeyDetailRequest.newBuilder()
            .setPixId(
                KeyDetailRequest.PixIdFilter.newBuilder()
                    .setClientId(clientId)
                    .setPixId(pixId)
                    .build()
            )
            .build()

        return try {
            grpcClient.detailKey(requestGrpc).let { responseGrpc ->
                val detailKeyResponse = DetailKeyResponse(
                    pixId = responseGrpc.pixId,
                    clientId = responseGrpc.clientId,
                    pixKey = PixKeyDetailResponse(
                        type = responseGrpc.pixKey.type,
                        key = responseGrpc.pixKey.key,
                        account = AccountDetailResponse(
                            type = responseGrpc.pixKey.account.type,
                            institution = responseGrpc.pixKey.account.institution,
                            cardholderName = responseGrpc.pixKey.account.cardholderName,
                            cardholderCpf = responseGrpc.pixKey.account.cardholderCpf,
                            agency = responseGrpc.pixKey.account.agency,
                            accountNumber = responseGrpc.pixKey.account.accountNumber
                        )
                    )
                )
                HttpResponse.ok(detailKeyResponse)
            }
        } catch (e: StatusRuntimeException) {

            val description = e.status.description
            val statusCode = e.status.code

            if (statusCode == Status.Code.NOT_FOUND) {
                throw HttpStatusException(HttpStatus.NOT_FOUND, description)
            }

            throw HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}