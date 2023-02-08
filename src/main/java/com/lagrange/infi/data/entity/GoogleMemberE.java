package com.lagrange.infi.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GoogleMemberE extends createdE{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
