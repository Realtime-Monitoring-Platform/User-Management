package com.realtime_monitoring.usermanag.grpc;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.realtime_monitoring.usermanag.model.User;
import com.realtime_monitoring.usermanag.model.Permission;
import com.realtime_monitoring.usermanag.repository.UserRepo;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepo userRepository;

    @Override
    @Transactional(readOnly = true)
    public void getUserById(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        try {

            if (request.getId() == null || request.getId().trim().isEmpty()) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("User ID cannot be empty")
                        .asRuntimeException());
                return;
            }

            UUID userId = UUID.fromString(request.getId().trim());

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> Status.NOT_FOUND
                            .withDescription("User not found with ID: " + request.getId())
                            .asRuntimeException());

            String roleName = "";
            List<String> permissions = List.of();

            if (user.getRole() != null) {
                if (user.getRole().getName() != null) {
                    roleName = user.getRole().getName();
                }

                if (user.getRole().getPermissions() != null) {
                    permissions = user.getRole().getPermissions().stream()
                            .filter(p -> p != null && p.getName() != null)
                            .map(Permission::getName)
                            .toList();
                }
            }

            GetUserResponse response = GetUserResponse.newBuilder()
                    .setId(request.getId())
                    .setUsername(user.getUsername() != null ? user.getUsername() : "")
                    .setEmail(user.getEmail() != null ? user.getEmail() : "")
                    .setTenantId(user.getTenantId() != null ? user.getTenantId().toString() : "")
                    .setEnabled(user.getStatus() != null && "ACTIVE".equalsIgnoreCase(user.getStatus().name()))
                    .setAddress(user.getAddress() != null ? user.getAddress() : "")
                    .setPhone(user.getPhone() != null ? user.getPhone() : "")
                    //.setFirstLogin(user.isFirstLogin())
                    .setLastName(user.getLastName() != null ? user.getLastName() : "")
                    .setFirstName(user.getFirstName() != null ? user.getFirstName() : "")
                    .setTeamName(user.getTeam() != null ? user.getTeam().getName() : ""   )
                    
                    .setRole(roleName)
                    .addAllPermissions(permissions)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            log.warn("Invalid UUID format received: {}", request.getId());
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid user ID format: " + request.getId())
                    .asRuntimeException());

        } catch (StatusRuntimeException e) {

            responseObserver.onError(e);

        } catch (Exception e) {

            log.error("Unexpected error in getUserById for ID: {}", request.getId(), e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error: " + e.getMessage())
                    .asRuntimeException());
        }
    }
}