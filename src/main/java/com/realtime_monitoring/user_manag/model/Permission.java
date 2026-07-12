package com.realtime_monitoring.user_manag.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
