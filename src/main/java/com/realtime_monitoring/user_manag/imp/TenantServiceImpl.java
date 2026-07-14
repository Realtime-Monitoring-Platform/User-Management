package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.dto.tenant.TenantRequest;
import com.realtime_monitoring.user_manag.dto.tenant.TenantResponse;
import com.realtime_monitoring.user_manag.mapper.TenantMapper;
import com.realtime_monitoring.user_manag.model.Tenant;
import com.realtime_monitoring.user_manag.repository.TenantRepository;
import com.realtime_monitoring.user_manag.service.TenantService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TenantServiceImpl implements TenantService {
    
    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;


    @Override
    public Page<TenantResponse> getAllTenants(Pageable pageable) {
        return this.tenantRepository.findAll(pageable).map(tenantMapper::toResponse);
    }

    @Override
    public TenantResponse createTenant(TenantRequest TenantRequest) {
        Tenant tenant = tenantMapper.toEntity(TenantRequest);
        return tenantMapper.toResponse(this.tenantRepository.save(tenant));
    }

    @Override
    public TenantResponse updateTenant(UUID TenantId, TenantRequest TenantRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTenant'");
    }

    

    @Override
    public void deleteTenant(UUID TenantId) {
        this.tenantRepository.deleteById(TenantId);;

    }

    @Override
    public TenantResponse getTenantById(UUID TenantId) {
        Tenant tenant = this.tenantRepository.findById(TenantId).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        return this.tenantMapper.toResponse(tenant);
    }
    
    
}
