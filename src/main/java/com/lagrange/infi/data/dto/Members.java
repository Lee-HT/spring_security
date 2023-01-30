package com.lagrange.infi.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Login DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Members {

    @NotNull
    private String id;

    @NotNull
    private String password;

}
