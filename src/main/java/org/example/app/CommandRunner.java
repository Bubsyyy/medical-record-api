package org.example.app;

import org.example.app.admin.AdminServiceImpl;
import org.example.app.diagnose.DiagnoseRepository;
import org.example.app.doctor.DoctorRepository;
import org.example.app.generalPractitioner.GeneralPractitionerRepository;
import org.example.app.medicalVisit.MedicalVisitRepository;
import org.example.app.sickLeave.SickLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {

    //Test purposes only
    //Admin service is for test purpose only too
    private final AdminServiceImpl adminService;
    private final DoctorRepository doctorRepository;
    private final GeneralPractitionerRepository generalPractitionerRepository;
    private final PasswordEncoder passwordEncoder;
    private final DiagnoseRepository diagnoseRepository;
    private final MedicalVisitRepository medicalVisitRepository;
    private final SickLeaveRepository sickLeaveRepository;


    @Autowired
    public CommandRunner(AdminServiceImpl adminService, DoctorRepository doctorRepository, GeneralPractitionerRepository generalPractitionerRepository, PasswordEncoder passwordEncoder, DiagnoseRepository diagnoseRepository, MedicalVisitRepository medicalVisitRepository, SickLeaveRepository sickLeaveRepository) {
        this.adminService = adminService;
        this.doctorRepository = doctorRepository;
        this.generalPractitionerRepository = generalPractitionerRepository;
        this.passwordEncoder = passwordEncoder;
        this.diagnoseRepository = diagnoseRepository;
        this.medicalVisitRepository = medicalVisitRepository;
        this.sickLeaveRepository = sickLeaveRepository;
    }

    @Override
    public void run(String... args) throws Exception {



        /*
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("lubomirlubomirovkn123@gmail.com");
        admin.setFirstName("Lyubomir");
        admin.setLastName("Lyubomirov");
        admin.setRole(Role.ADMIN);
        admin.setPersonalNumber("1234567899");
        adminService.save(admin);

         */





        /*

        Doctor doctor = new Doctor();
        doctor.setRole(Role.DOCTOR);
        doctor.setRegistrationNumber("alaalalala");
        doctor.setFirstName("Boki");
        doctor.setLastName("Doki");
        doctor.setEmail("boki@gmail.com");
        doctor.setUsername("Bobi the doctor");
        doctor.setPassword("$2a$12$ouNaPtF8Cqqkn29YwOXGy.xQYtpSXMSQi1Jd.Mx6toIcigEn0w1Ou"); //doci
        doctor.setPersonalNumber("9090909090");
        doctorRepository.save(doctor);

         */





        /*
        Random random = new Random();

        GeneralPractitioner generalPractitioner = new GeneralPractitioner();

        generalPractitioner.setFirstName("John");
        generalPractitioner.setLastName("Doe");
        generalPractitioner.setEmail("johndoe" + random.nextInt(1000) + "@gmail.com");
        generalPractitioner.setUsername("john_doe_" + random.nextInt(1000));
        generalPractitioner.setPassword("password"); // Random password
        generalPractitioner.setRegistrationNumber("RN" + random.nextInt(10000)); // Random registration number
        generalPractitioner.setPersonalNumber("1234567777"); // Random personal number
        generalPractitioner.setProvidesHomeVisits(random.nextBoolean()); // Random boolean for home visits
        generalPractitioner.setRegisteredPatients(new HashSet<>()); // Empty set for now
        generalPractitioner.setSpecialities(new HashSet<>()); // Empty set for now
        generalPractitioner.setMedicalVisits(new HashSet<>());// Empty set for now

        generalPractitioner.setRole(Role.DOCTOR);

        // Save the GeneralPractitioner
        generalPractitionerRepository.save(generalPractitioner);

         */



        /*
        List<Object[]> objects = generalPractitionerRepository.countPatientsPerGP();//Patients count per gp
        System.out.println(objects);

         */



        /*
        Diagnose latestDiagnose = diagnoseRepository.findLatestDiagnose();//Latest illness
        System.out.println("latestDiagnose: " + latestDiagnose);

         */



        /*
        List<Object[]> objects = doctorRepository.countVisitsPerDoctor();
        System.out.println(objects);

         */



        /*

        List<MedicalVisit> visitsWithinPeriod = medicalVisitRepository.findVisitsWithinPeriod(LocalDate.now().minusYears(1), LocalDate.now().plusYears(2));

        System.out.println();

         */


        /*
        Object[] monthWithMostSickLeaves = sickLeaveRepository.findMonthWithMostSickLeaves();
        System.out.println();

         */






        /*
        List<Object[]> doctorsWithMostSickLeaves = medicalVisitRepository.findDoctorsWithMostSickLeaves();
        System.out.println();

         */

        /*
        Object[] monthWithMostSickLeaves = sickLeaveRepository.findMonthWithMostSickLeaves();
        System.out.println(monthWithMostSickLeaves[0][0]);
        System.out.println(monthWithMostSickLeaves[0][1]);
        System.out.println();

         */


    }
}
