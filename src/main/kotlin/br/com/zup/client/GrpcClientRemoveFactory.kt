package br.com.zup.client

import br.com.zup.KeyManagerRemoveGRPCServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientRemoveFactory {

    @Singleton
    fun keyManagerRestClientStub(@GrpcChannel("keymanager") channel: ManagedChannel): KeyManagerRemoveGRPCServiceGrpc.KeyManagerRemoveGRPCServiceBlockingStub?{
        return KeyManagerRemoveGRPCServiceGrpc.newBlockingStub(channel)
    }
}