package org.example.medical_record.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetRequest {

    private String username;
    private String currentPassword;
    private String newPassword;

}
