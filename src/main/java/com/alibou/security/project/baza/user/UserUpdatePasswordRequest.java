package com.alibou.security.project.baza.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePasswordRequest {

    @NotBlank
    @Schema(example = "oldPassword")
    private String oldPassword;

    @NotBlank
    @Schema(example = "newPassword")
    private String newPassword;

    @NotBlank
    @Schema(example = "newPassword")
    private String repeatPassword;
}