package org.example.app.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpExpose {
    private String firstName;
    private String lastName;
    private boolean providesHomeVisits;
}
