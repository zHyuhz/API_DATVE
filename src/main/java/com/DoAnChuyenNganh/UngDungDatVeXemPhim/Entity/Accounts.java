package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;       // PK, varchar(50), not null 
    @Column(name = "password", length = 255, nullable = false)
    private String password;   // varchar(255), not null    
    @Column(name = "email", length = 100, nullable = false)
    private String email;          // varchar(100), not null
   
    @Column(name = "createAt")
    private LocalDateTime createdAt;        // datetime, nullable    
    @Column(name = "status")
    private int status;        // bit, nullable    
    @Column(name = "role", length = 20)
    private String accountRole;    // nvarchar(10), nullable
    @JsonBackReference
    @OneToOne(mappedBy = "accounts", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;
}


