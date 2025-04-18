package org.example.app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {

    @NotNull
    private String userEmail;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;
}
