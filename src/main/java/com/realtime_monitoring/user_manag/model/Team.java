package com.realtime_monitoring.user_manag.model;


import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name="teams")
@Getter
@Setter
public class Team extends BaseEntity {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

}
