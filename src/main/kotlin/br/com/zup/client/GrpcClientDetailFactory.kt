package br.com.zup.client

import br.com.zup.KeyManagerDetailGRPCServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientDetailFactory {

    @Singleton
    fun keyManagerRestClientStub(@GrpcChannel("keymanager") channel: ManagedChannel): KeyManagerDetailGRPCServiceGrpc.KeyManagerDetailGRPCServiceBlockingStub?{
        return KeyManagerDetailGRPCServiceGrpc.newBlockingStub(channel)
    }
}