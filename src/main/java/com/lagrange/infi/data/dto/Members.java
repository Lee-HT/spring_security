package com.lagrange.infi.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Login DTO")
@Data
public class Members {

    @NotNull
    private Long idx;

    @NotNull
    private String id;

    @NotNull
    private String password;

}
