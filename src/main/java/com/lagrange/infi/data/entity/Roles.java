package com.lagrange.infi.data.entity;

import jakarta.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    private String role;

    public List<String> getRoles(){
        return Arrays.asList(this.role.split(","));
    }

    public String getRole(){
        return role;
    }


}
