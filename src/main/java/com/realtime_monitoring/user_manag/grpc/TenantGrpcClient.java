package com.realtime_monitoring.user_manag.grpc;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.dto.tenant.TenantDto;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class TenantGrpcClient {

    private final TenantServiceGrpc.TenantServiceBlockingStub stub;

    public TenantGrpcClient(@GrpcClient("tenant-service") TenantServiceGrpc.TenantServiceBlockingStub stub) {
        this.stub = stub;
    }

    public Map<UUID, TenantDto> getTenants(List<UUID> ids) {
        GetTenantsRequest request = GetTenantsRequest.newBuilder().addAllTenantIds(ids.stream().map(UUID::toString).toList()).build();
        GetTenantsResponse response = stub.getTenantsByIds(request);
        return response
                .getTenantsList()
                .stream()
                .collect(Collectors.toMap(t -> UUID.fromString(t.getId()),
                t -> new TenantDto(UUID.fromString(t.getId()),t.getName(),t.getStatus())));
    }

}