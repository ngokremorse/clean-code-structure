package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentSignDto {
    @NotEmpty(message = "DocumentSignDto.userId không được để trống")
    @Schema(description = "Người ký")
    private String userId;
    @NotNull(message = "DocumentSignDto.level không được để trống")
    @Schema(description = "Cấp phê duyệt")
    private Integer level;
    @NotEmpty
    @Schema(description = "File ký, được lưu trên camunda")
    private String fileVariableName; // File thực hiện ký
    @Schema(description = "Vị trí ký, kiểu ký, thông số kỹ thuật")
    private List<SignatureDto> signatures; // Các chữ ký
    @Schema(description = "Vị trí text, thông số kỹ thuật")
    private List<DNote> notes; // Các text
    @Schema(description = "Các thông tin khác nếu có")
    private List<Map<String, Object>> others; // Các thông tin khác
}
