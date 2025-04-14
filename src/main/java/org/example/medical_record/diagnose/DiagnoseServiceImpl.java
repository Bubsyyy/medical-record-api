package org.example.medical_record.diagnose;

import lombok.extern.slf4j.Slf4j;
import org.example.medical_record.exception.DiagnoseNotFoundException;
import org.example.medical_record.web.dto.DiagnoseCreationRequest;
import org.example.medical_record.web.dto.DiagnoseExpose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DiagnoseServiceImpl implements DiagnoseService {

    private final DiagnoseRepository diagnoseRepository;

    @Autowired
    public DiagnoseServiceImpl(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public Diagnose getDiagnoseByName(String diagnoseName) {

        Optional<Diagnose> diagnoseOptional = diagnoseRepository.findByName(diagnoseName);

        if (diagnoseOptional.isPresent()) {

            return diagnoseOptional.get();
        }

        Diagnose diagnose = Diagnose.builder().name(diagnoseName).build();
        diagnoseRepository.save(diagnose);

        log.info("New diagnose found [%s]".formatted(diagnose.getName()));

        return diagnose;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DiagnoseExpose> getAllDiagnoses() {

        List<Diagnose> allDiagnoses = diagnoseRepository.findAll();



        return allDiagnoses.stream().map(DiagnoseMapper::mapDiagnoseToExpose).toList();



    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public DiagnoseExpose getDiagnosesById(Long id) {

        Optional<Diagnose> diagnoseOptional = diagnoseRepository.findById(id);

        if (diagnoseOptional.isEmpty()) {
            throw new DiagnoseNotFoundException("There is no such doctor with id: " + id);
        }

        Diagnose diagnose = diagnoseOptional.get();

        return DiagnoseMapper.mapDiagnoseToExpose(diagnose);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DiagnoseExpose createNewDiagnose(DiagnoseCreationRequest request) {

        Diagnose diagnose = Diagnose.builder()
                .name(request.getDiagnose())
                .dateOfFoundation(request.getDateOfFoundation())
                .build();

        diagnoseRepository.save(diagnose);


        return DiagnoseMapper.mapDiagnoseToExpose(diagnose);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DiagnoseExpose deleteDiagnoseById(Long id) {

        Optional<Diagnose> diagnoseOptional = diagnoseRepository.findById(id);

        if (diagnoseOptional.isEmpty()) {
            throw new DiagnoseNotFoundException("There is no such doctor with id: " + id);
        }

        Diagnose diagnose = diagnoseOptional.get();
        diagnoseRepository.delete(diagnose);



        return DiagnoseMapper.mapDiagnoseToExpose(diagnose);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public DiagnoseExpose getLastDiagnose() {

        Diagnose latestDiagnose = diagnoseRepository.findLatestDiagnose();

        return DiagnoseMapper.mapDiagnoseToExpose(latestDiagnose);
    }
}
