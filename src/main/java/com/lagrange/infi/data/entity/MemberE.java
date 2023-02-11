package com.lagrange.infi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "member")
public class MemberE extends createdE{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(nullable = true)
    private String provider;

    @Column(nullable = true)
    private String providerid;

    public void updateInfo(String password){
        this.password = password;
    }

}
