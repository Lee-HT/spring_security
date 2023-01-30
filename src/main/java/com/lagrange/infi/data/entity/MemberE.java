package com.lagrange.infi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "member")
public class MemberE extends createdE{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idx;

    @Column(unique = true,nullable = false,length = 20)
    private String id;

    @Column(nullable = false,length = 30)
    private String password;

}
