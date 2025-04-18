package org.example.medical_record.sickLeave;

import org.example.medical_record.exception.SickLeaveNotFoundException;
import org.example.medical_record.web.dto.MonthWithSickLeaves;
import org.example.medical_record.web.dto.SickLeaveEditRequest;
import org.example.medical_record.web.dto.SickLeaveExpose;
import org.example.medical_record.web.dto.SickLeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SickLeaveServiceImpl implements SickLeaveService{

    private final SickLeaveRepository sickLeaveRepository;

    @Autowired
    public SickLeaveServiceImpl(SickLeaveRepository sickLeaveRepository) {
        this.sickLeaveRepository = sickLeaveRepository;
    }


    @Override
    public SickLeave initializeSickLeave(SickLeaveRequest sickLeaveRequest) {

        LocalDate startDate = sickLeaveRequest.getStartDate();
        int daysToRest = sickLeaveRequest.getDays();
        LocalDate endDate = startDate.plusDays(daysToRest);

        String reason = sickLeaveRequest.getReason();

        SickLeave sickLeave = SickLeave.builder()
                .startTime(startDate)
                .reason(reason)
                .endTime(endDate)
                .build();

        sickLeaveRepository.save(sickLeave);

        return sickLeave;
    }

    @Override
    public List<SickLeaveExpose> getAllSickLeaves() {

        List<SickLeave> sickLeaves = sickLeaveRepository.findAll();

        return sickLeaves.stream().map(SickLeaveMapper::mapSickLeaveToExpose).toList();

    }

    @Override
    public SickLeaveExpose getSickLeave(Long id) {
        Optional<SickLeave> sickLeaveOptional = sickLeaveRepository.findById(id);
        if (sickLeaveOptional.isEmpty()) {
            throw new SickLeaveNotFoundException("There is no such sick leave with id: " + id);
        }

        SickLeave sickLeave = sickLeaveOptional.get();

        return SickLeaveMapper.mapSickLeaveToExpose(sickLeave);
    }

    @Override
    public SickLeaveExpose deleteSickLeave(Long id) {


        Optional<SickLeave> sickLeaveOptional = sickLeaveRepository.findById(id);
        if (sickLeaveOptional.isEmpty()) {
            throw new SickLeaveNotFoundException("There is no such sick leave with id: " + id);
        }

        SickLeave sickLeave = sickLeaveOptional.get();

        sickLeaveRepository.delete(sickLeave);

        return SickLeaveMapper.mapSickLeaveToExpose(sickLeave);
    }

    @Override
    public MonthWithSickLeaves getMonthWithMostSickLeaveCount() {

        Object[] monthWithMostSickLeaves = sickLeaveRepository.findMonthWithMostSickLeaves();

        Object[] month = (Object[]) monthWithMostSickLeaves[0];

        int monthNumber = ((Number) month[0]).intValue();
        int count = ((Number) month[1]).intValue();

        return new MonthWithSickLeaves(monthNumber, count);
    }

    @Override
    public SickLeaveExpose updateSickLeave(Long id, SickLeaveEditRequest request) {

        Optional<SickLeave> sickLeaveOptional = sickLeaveRepository.findById(id);
        if (sickLeaveOptional.isEmpty()) {
            throw new SickLeaveNotFoundException("There is no such sick leave with id: " + id);
        }

        SickLeave sickLeave = sickLeaveOptional.get();

        sickLeave.setStartTime(request.getStartDate());
        sickLeave.setEndTime(request.getEndDate());

        sickLeaveRepository.save(sickLeave);


        return SickLeaveMapper.mapSickLeaveToExpose(sickLeave);
    }
}
