package org.example.medical_record.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalVisitCreationRequest {


   @NotNull
   private Long doctorId;

   @NotNull
   private Long patientId;

   @NotNull
   private LocalDate dateOfVisitation;

}
