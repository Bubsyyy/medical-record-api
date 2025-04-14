# medical-record-api

🏥 Medical Record System
A Spring Boot web application for managing patient medical records, medical visits, doctors, and statistics — complete with secure role-based access control.

📌 Project Overview
This system helps clinics and hospitals track:

Patients' medical histories, diagnoses, treatments, and sick leave periods.

Doctors and their specializations, including General Practitioners (GPs).

Patient visits to doctors, with optional sick leave issuance.

Reports and statistics for better healthcare management.

✅ Features


🔁 CRUD Operations
Doctors

Patients

Diagnoses

Medical Visits

Sick Leave Records

📊 Reports & Statistics

Patients with a specific diagnosis

Most frequently diagnosed illnesses

Patients under a specific GP

Patient count per GP

Visit count per doctor

Visit history per patient

Visit history within a specific period (all or specific doctor)

Month with the highest number of sick leave records

Doctors who issued the most sick leaves

🔐 Roles & Permissions

Patient: View personal medical history

Doctor: View all data; modify records of own patients only

Admin: Full access to all data and operations