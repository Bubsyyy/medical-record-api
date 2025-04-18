package org.example.app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnoseCreationRequest {

    @NotBlank
    private String diagnose;
    @NotNull
    private LocalDate dateOfFoundation;

}
