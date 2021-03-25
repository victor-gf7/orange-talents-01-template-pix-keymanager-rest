package br.com.zup.client

import br.com.zup.KeyManagerListGRPCServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientListFactory {

    @Singleton
    fun keyManagerRestClientStub(@GrpcChannel("keymanager") channel: ManagedChannel): KeyManagerListGRPCServiceGrpc.KeyManagerListGRPCServiceBlockingStub?{
        return KeyManagerListGRPCServiceGrpc.newBlockingStub(channel)
    }
}