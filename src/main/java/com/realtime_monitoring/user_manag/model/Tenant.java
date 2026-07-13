package com.realtime_monitoring.user_manag.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "tenants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tenant extends BaseEntity {


    private String name;

    private String domain;

    private String logoUrl;
    @Enumerated(EnumType.STRING)
    private TenantStatus Status;
    
}
