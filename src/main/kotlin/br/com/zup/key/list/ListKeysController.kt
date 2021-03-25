package br.com.zup.key.list

import br.com.zup.KeyManagerListGRPCServiceGrpc
import br.com.zup.ListKeysRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@Controller("/api/v1/key")
class ListKeysController(
    @Inject val grpcClient: KeyManagerListGRPCServiceGrpc.KeyManagerListGRPCServiceBlockingStub
) {


    @Get("list/{clientId}/keys")
    fun listKeys(@PathVariable clientId: String): MutableHttpResponse<ConsumerKeys> {


        val requestGrpc = ListKeysRequest.newBuilder()
            .setClientId(clientId)
            .build()

        val consumerKeys = grpcClient.listKeys(requestGrpc).let { responseGrpc ->
            ConsumerKeys(
                clientId = responseGrpc.clientId,
                pixKey = responseGrpc.keysList.map {
                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    val instant: Instant = Instant.ofEpochSecond(
                        it.createdAt.seconds,
                        it.createdAt.nanos.toLong()
                    )
                    ConsumerListKeysResponse(
                        pixId = it.pixId,
                        type = it.keyType,
                        key = it.key,
                        accountType = it.accountType,
                        registeredAt = instant.atZone(ZoneId.of("UTC")).toLocalDateTime().format(formatter)
                    )
                }
            )
        }

        return HttpResponse.ok(consumerKeys)
    }
}