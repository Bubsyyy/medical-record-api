package org.example.app.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitHistoryPeriodRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
