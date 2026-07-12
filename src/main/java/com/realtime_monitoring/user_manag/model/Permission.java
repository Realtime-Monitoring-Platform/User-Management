package com.realtime_monitoring.user_manag.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="permissions")
public class Permission extends BaseEntity {
    

    private String name;
    private String description;
    private String resource;
    private String action;
    
}
