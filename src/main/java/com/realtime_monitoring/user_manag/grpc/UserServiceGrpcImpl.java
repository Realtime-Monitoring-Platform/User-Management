package com.realtime_monitoring.user_manag.grpc;

import java.util.UUID;

import com.realtime_monitoring.user_manag.model.User;
import com.realtime_monitoring.user_manag.repository.UserRepo;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepo userRepository;

    @Override
    public void getUserById(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        try {
            UUID userId = UUID.fromString(String.valueOf(request.getId()));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> Status.NOT_FOUND
                            .withDescription("User not found with ID: " + request.getId())
                            .asRuntimeException());

            GetUserResponse response = GetUserResponse.newBuilder()
                    .setId(request.getId())
                    .setUsername(user.getUsername())
                    .setEmail(user.getEmail())
                    .setTenantId(user.getTenantId() != null ? user.getTenantId().toString() : "")
                    .setEnabled(user.getStatus() != null && user.getStatus().name().equals("ACTIVE"))
                    .addAllRoles(user.getRole() != null ? java.util.List.of(user.getRole().getName()) : java.util.List.of())
                    .addAllPermissions(java.util.List.of())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid user ID format: " + request.getId())
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .asRuntimeException());
        }
    }
}