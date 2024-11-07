package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Approver {
    @NotBlank(message = "acc không được để trống")
    @Size(max = 20, message = "acc dài tối đa 20 ký tự")
    String acc;
    @NotNull(message = "Approver.level không được để trống")
    Integer level;
    String action;
    String comment;
    String email;
    String fullName;
    String department;
}
