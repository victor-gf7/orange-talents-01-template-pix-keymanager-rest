package br.com.zup.key.remove

import br.com.zup.KeyManagerRemoveGRPCServiceGrpc
import br.com.zup.RemoveKeyRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/api/v1/key")
class RemoveKeyController(@Inject val grpcClient: KeyManagerRemoveGRPCServiceGrpc.KeyManagerRemoveGRPCServiceBlockingStub) {

    @Delete("/remove/{pixId}")
    fun removeKey(@Body @Valid request: RemoveRequest, @PathVariable pixId: String): HttpResponse<RemoveResponse> {

        val requestGRPC = RemoveKeyRequest.newBuilder()
            .setClientId(request.clientId)
            .setPixId(pixId)
            .build()

        try {
            val responseGRPC = grpcClient.removeKey(requestGRPC)
            val response = RemoveResponse(responseGRPC.pixId, responseGRPC.clientId)
            return HttpResponse.ok(response)
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
