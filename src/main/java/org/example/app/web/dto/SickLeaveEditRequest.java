package org.example.app.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SickLeaveEditRequest {

    private LocalDate startDate;
    private LocalDate endDate;
}
