package br.com.zup.client

import br.com.zup.KeyManagerRegisterGRPCServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientRegisterFactory {

    @Singleton
    fun keyManagerRestClientStub(@GrpcChannel("keymanager") channel: ManagedChannel): KeyManagerRegisterGRPCServiceGrpc.KeyManagerRegisterGRPCServiceBlockingStub?{
        return KeyManagerRegisterGRPCServiceGrpc.newBlockingStub(channel)
    }
}