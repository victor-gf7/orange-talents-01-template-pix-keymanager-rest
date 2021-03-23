package br.com.zup.key.register

import br.com.zup.KeyManagerRegisterGRPCServiceGrpc
import br.com.zup.KeyType
import br.com.zup.RegisterKeyRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/api/v1/key")
class RegisterKeyController(@Inject val grpcClient: KeyManagerRegisterGRPCServiceGrpc.KeyManagerRegisterGRPCServiceBlockingStub) {

    @Post("/register/{clientId}")
    fun registerKey(@Body @Valid request: NewKeyRequest, @PathVariable clientId: String): HttpResponse<PixKeyResponse>{
        val requestGrpc = RegisterKeyRequest.newBuilder()
            .setIdClient(clientId)
            .setKeyType(request.keyType)
            .setKeyValue(if (request.keyType == KeyType.RANDOM_KEY) "" else request.keyValue)
            .setAccountType(request.accountType)
            .build()


        try {
            val responseGRPC = grpcClient.registerKey(requestGrpc)
            val pixKeyResponse = PixKeyResponse(responseGRPC.clientId, responseGRPC.pixId)

            return HttpResponse.ok(pixKeyResponse)
        } catch (e: StatusRuntimeException) {
            val description = e.status.description
            val statusCode = e.status.code

            if (statusCode == Status.Code.ALREADY_EXISTS) {
                throw HttpStatusException(HttpStatus.BAD_REQUEST, description)
            }

            throw HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }

    }
}