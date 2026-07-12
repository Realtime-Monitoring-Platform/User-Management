package com.realtime_monitoring.user_manag.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String username;

    private String email;


    private String firstName;

    private String lastName;


    private String phone;

    private String avatarUrl;


    @Enumerated(EnumType.STRING)
    private UserStatus status;


    private LocalDateTime lastLogin;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;



    @ManyToOne
    @JoinColumn(name ="tenant_id")
    private Tenant tenant;
    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")

    )
    private Set<Role> roles = new HashSet<>();





    // @ManyToMany
    // @JoinTable(
    //     name="user_roles",
    //     joinColumns=@JoinColumn(name="user_id"),
    //     inverseJoinColumns=@JoinColumn(name="role_id")
    // )
    // private Set<Role> roles = new HashSet<>();



    // @ManyToMany
    // @JoinTable(
    //     name="user_teams",
    //     joinColumns=@JoinColumn(name="user_id"),
    //     inverseJoinColumns=@JoinColumn(name="team_id")
    // )
    // private Set<Team> teams = new HashSet<>();

}