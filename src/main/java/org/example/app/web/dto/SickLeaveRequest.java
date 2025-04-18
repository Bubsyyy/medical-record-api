package org.example.app.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SickLeaveRequest {



    private int days;

    private String reason;

    private LocalDate startDate;

}
