package com.lagrange.infi.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberD {

    private Long id;

    @NotNull
    private String userid;

    @NotNull
    private String password;

    @Nullable
    private String email;

}
