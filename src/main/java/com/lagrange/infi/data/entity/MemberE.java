package com.lagrange.infi.data.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberE {

    @NotNull
    private Long idx;

    @NotNull
    private String id;

    @NotNull
    private String password;

}
