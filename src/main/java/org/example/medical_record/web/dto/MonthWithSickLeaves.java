package org.example.medical_record.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthWithSickLeaves {

    private int month;
    private int leavesCount;
}
