package org.example.app.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SickLeaveExpose {

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
