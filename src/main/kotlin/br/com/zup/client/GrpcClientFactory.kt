package br.com.zup.client

import br.com.zup.KeyManagerGRPCServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientFactory {

    @Singleton
    fun keyManagerRestClientStub(@GrpcChannel("keymanager") channel: ManagedChannel): KeyManagerGRPCServiceGrpc.KeyManagerGRPCServiceBlockingStub?{
        return KeyManagerGRPCServiceGrpc.newBlockingStub(channel)
    }
}