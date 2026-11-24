import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HospitalManagement {

    // ArrayLists
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Medical> medicals;
    private ArrayList<Lab> laboratories;
    private ArrayList<Facility> facilities;
    private ArrayList<Staff> staffs;
    private ArrayList<Ambulance> ambulances;


    // Constructor
    public HospitalManagement() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        medicals = new ArrayList<>();
        laboratories = new ArrayList<>();
        facilities = new ArrayList<>();
        staffs = new ArrayList<>();
        ambulances = new ArrayList<>();
        
        initializeData();
    }


    // Initial Sample Data
    private void initializeData() {

        // Doctors
        doctors.add(new Doctor("175", "Leong Di Hao", "Surgeon", "8-11AM", "MBBS, MD", 11));
        doctors.add(new Doctor("265", "Liew Jia Min", "Physician", "10AM-3PM", "MBBS, MS", 45));
        doctors.add(new Doctor("661", "Yew Kai Qian", "Surgeon", "7-11AM", "MBBS, MD", 8));
        doctors.add(new Doctor("861", "Chong Wei Ni", "Cardiologist", "9AM-1PM", "MBBS, MRCP", 20));
        doctors.add(new Doctor("238", "Sarah Lee", "Dermatologist", "1-5PM", "MBBS, MD", 15));

        // Patients
        patients.add(new Patient("175", "Leong Di Hao", "Fever", "Male", "Admitted", 21));
        patients.add(new Patient("265", "Liew Jia Min", "Asthma", "Female", "Admitted", 21));
        patients.add(new Patient("661", "Yew Kai Qian", "Migraine", "Female", "Discharged", 21));
        patients.add(new Patient("861", "Chong Wei Ni", "Flu", "Female", "Admitted", 21));
        patients.add(new Patient("735", "Mei Ling", "Hypertension", "Female", "Admitted", 50));

        // Medical
        medicals.add(new Medical("Paracetamol", "Pharmaniaga", "12/2027", 10, 100));
        medicals.add(new Medical("Amoxicillin", "Kotra Pharma", "08/2027", 25, 80));
        medicals.add(new Medical("Ibuprofen", "Duopharma", "03/2028", 15, 120));
        medicals.add(new Medical("Cetirizine", "Xepa", "06/2027", 12, 90));
        medicals.add(new Medical("Aspirin", "Pharmaniaga", "10/2028", 8, 150));

        // Laboratories
        laboratories.add(new Lab("Pathology Lab", 500));
        laboratories.add(new Lab("X-Ray Lab", 300));
        laboratories.add(new Lab("Blood Test Lab", 200));
        laboratories.add(new Lab("MRI Lab", 800));
        laboratories.add(new Lab("CT Scan Lab", 700));

        // Facilities
        facilities.add(new Facility("Emergency Room"));
        facilities.add(new Facility("ICU"));
        facilities.add(new Facility("Operation Theatre"));
        facilities.add(new Facility("Pharmacy"));
        facilities.add(new Facility("Radiology Room"));

        // Staff
        staffs.add(new Staff("175", "Leong Di Hao", "Administrator", "Male", 3500));
        staffs.add(new Staff("265", "Liew Jia Min", "Nurse", "Female", 3000));
        staffs.add(new Staff("661", "Yew Kai Qian", "Receptionist", "Female", 2500));
        staffs.add(new Staff("861", "Chong Wei Ni", "Accountant", "Female", 3200));
        staffs.add(new Staff("423", "Alex Tan", "Technician", "Male", 3800));

        // Ambulances - Application Extension
        ambulances.add(new Ambulance("A01", "Emergency Ambulance", "VAN-101", 4));
        ambulances.add(new Ambulance("A02", "Patient Transport", "VAN-102", 6));
        ambulances.add(new Ambulance("A03", "ICU Ambulance", "VAN-103", 3));
    }

    // Welcome Message
    private void displayWelcome() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  hh:mm a");

        System.out.println();
        System.out.println("============================================================");
        System.out.println("               HOSPITAL MANAGEMENT SYSTEM                   ");
        System.out.println("                    HMS Admin Console                       ");
        System.out.println("============================================================");
        System.out.println();
        System.out.println("  Welcome to HMS");
        System.out.println("  Date & Time : " + currentDateTime.format(formatter)
        );
    }

    // Main Menu
    private void displayMainMenu() {
        System.out.println();
        System.out.println("+---------------------- MAIN MENU -------------------------+");
        System.out.println("|                                                          |");
        System.out.println("|   1  Doctors                  4  Laboratories            |");
        System.out.println("|   2  Patients                 5  Facilities              |");
        System.out.println("|   3  Medical                  6  Staff                   |");
        System.out.println("|                                                          |");
        System.out.println("|   0  Exit                                                |");
        System.out.println("|                                                          |");
        System.out.println("+----------------------------------------------------------+");
        System.out.print(" Select an option > ");
    }


    // Start HMS
    public void start() {
        displayWelcome();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    doctorMenu();
                    break;

                case "2":
                    patientMenu();
                    break;

                case "3":
                    medicalMenu();
                    break;

                case "4":
                    laboratoryMenu();
                    break;

                case "5":
                    facilityMenu();
                    break;

                case "6":
                    staffMenu();
                    break;

                case "0":
                    System.out.println();
                    System.out.println("------------------------------------------------------------");
                    System.out.println("             Thank you for using the HMS. Goodbye!          ");
                    System.out.println("------------------------------------------------------------");

                    running = false;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select from 0 to 6.");
            }
        }
    }

    // Doctor Menu
    private void doctorMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                        DOCTOR MENU                         ");
            System.out.println("------------------------------------------------------------");
            System.out.println();
            System.out.println("   1  View Doctors");
            System.out.println("   2  Add New Doctor");
            System.out.println();
            System.out.println("   0  Back to Main Menu");
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.print(" Select an option > ");

            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    displayDoctors();
                    break;

                case "2":
                    addNewDoctor();
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select 0, 1 or 2.");
            }
        }
    }

    // Display Doctors
    private void displayDoctors() {
        System.out.println();
        System.out.println("                                DOCTOR LIST                                                  ");
        System.out.println("+--------+--------------------+-----------------+-------------+--------------------+--------+");
        System.out.printf(
                "| %-6s | %-18s | %-15s | %-11s | %-18s | %-6s |%n",
                "ID",
                "Name",
                "Specialist",
                "Work Time",
                "Qualification",
                "Room"
        );
        System.out.println("+--------+--------------------+-----------------+-------------+--------------------+--------+");

        for (int i = 0; i < doctors.size(); i++) {
            doctors.get(i).showDoctorInfo();
        }

        System.out.println("+--------+--------------------+-----------------+-------------+--------------------+--------+");
    }

    // Add New Doctor
    private void addNewDoctor() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("                      ADD NEW DOCTOR                        ");
        System.out.println("------------------------------------------------------------");
        
        ArrayList<String> existingIds = new ArrayList<>();
        for (Doctor d : doctors) {
            existingIds.add(d.getId());
        }

        Doctor newDoctor = new Doctor();
        newDoctor.newDoctor(existingIds);
        doctors.add(newDoctor);

        System.out.println();
        System.out.println(" Doctor added successfully.");
        System.out.println();
        System.out.println(" NEW RECORD");
        System.out.println("+--------+--------------------+-----------------+-------------+--------------------+--------+");
        System.out.printf(
                "| %-6s | %-18s | %-15s | %-11s | %-18s | %-6s |%n",
                "ID",
                "Name",
                "Specialist",
                "Work Time",
                "Qualification",
                "Room"
        );
        System.out.println("+--------+--------------------+-----------------+-------------+--------------------+--------+");
        newDoctor.showDoctorInfo();
        System.out.println("+--------+--------------------+-----------------+-------------+--------------------+--------+");
    }

    // Patient Menu
    private void patientMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                        PATIENT MENU                        ");
            System.out.println("------------------------------------------------------------");
            System.out.println();
            System.out.println("   1  View Patients");
            System.out.println("   2  Add New Patient");
            System.out.println();
            System.out.println("   0  Back to Main Menu");
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.print(" Select an option > ");

            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    displayPatients();
                    break;

                case "2":
                    addNewPatient();
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select 0, 1 or 2.");
            }
        }
    }

    // Display Patients
    private void displayPatients() {
        System.out.println();
        System.out.println("                                PATIENT LIST                                         ");
        System.out.println("+--------+--------------------+-----------------+------------+--------------+-------+");
        System.out.printf(
                "| %-6s | %-18s | %-15s | %-10s | %-12s | %-5s |%n",
                "ID",
                "Name",
                "Disease",
                "Sex",
                "Admit Status",
                "Age"
        );
        System.out.println("+--------+--------------------+-----------------+------------+--------------+-------+");

        for (int i = 0; i < patients.size(); i++) {
            patients.get(i).showPatientInfo();
        }
        
        System.out.println("+--------+--------------------+-----------------+------------+--------------+-------+");
    }

    // Add New Patient
    private void addNewPatient() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("                      ADD NEW PATIENT                       ");
        System.out.println("------------------------------------------------------------");
        
        ArrayList<String> existingIds = new ArrayList<>();
        for (Patient p : patients) {
            existingIds.add(p.getId());
        }
        
        Patient newPatient = new Patient();
        newPatient.newPatient(existingIds);
        patients.add(newPatient);

        System.out.println();
        System.out.println(" Patient added successfully.");
        System.out.println();
        System.out.println(" NEW RECORD");
        System.out.println("+--------+--------------------+-----------------+------------+--------------+-------+");
        System.out.printf(
                "| %-6s | %-18s | %-15s | %-10s | %-12s | %-5s |%n",
                "ID",
                "Name",
                "Disease",
                "Sex",
                "Admit Status",
                "Age"
        );
        System.out.println("+--------+--------------------+-----------------+------------+--------------+-------+");
        newPatient.showPatientInfo();
        System.out.println("+--------+--------------------+-----------------+------------+--------------+-------+");
    }

    // Medical Menu
    private void medicalMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                        MEDICAL MENU                        ");
            System.out.println("------------------------------------------------------------");
            System.out.println();
            System.out.println("   1  View Medical");
            System.out.println("   2  Add New Medical");
            System.out.println();
            System.out.println("   0  Back to Main Menu");
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.print(" Select an option > ");

            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    displayMedicals();
                    break;

                case "2":
                    addNewMedical();
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select 0, 1 or 2.");
            }
        }
    }

    // Display Medical
    private void displayMedicals() {
        System.out.println();
        System.out.println("                           MEDICAL LIST                                         ");
        System.out.println("+--------------------+--------------------+--------------+----------+----------+");
        System.out.printf(
                "| %-18s | %-18s | %-12s | %-8s | %-8s |%n",
                "Name",
                "Manufacturer",
                "Expiry Date",
                "Cost",
                "Count"
        );
        System.out.println("+--------------------+--------------------+--------------+----------+----------+");

        for (int i = 0; i < medicals.size(); i++) {
            medicals.get(i).findMedical();
        }
        
        System.out.println("+--------------------+--------------------+--------------+----------+----------+");
    }

    // Add New Medical
    private void addNewMedical() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("                      ADD NEW MEDICAL                       ");
        System.out.println("------------------------------------------------------------");

        Medical newMedical = new Medical();
        newMedical.newMedical();
        medicals.add(newMedical);

        System.out.println();
        System.out.println(" Medical added successfully.");
        System.out.println();
        System.out.println(" NEW RECORD");
        System.out.println("+--------------------+--------------------+--------------+----------+----------+");
        System.out.printf(
                "| %-18s | %-18s | %-12s | %-8s | %-8s |%n",
                "Name",
                "Manufacturer",
                "Expiry Date",
                "Cost",
                "Count"
        );
        System.out.println("+--------------------+--------------------+--------------+----------+----------+");
        newMedical.findMedical();
        System.out.println("+--------------------+--------------------+--------------+----------+----------+");
    }
    
    // Laboratory Menu
    private void laboratoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                      LABORATORY MENU                       ");
            System.out.println("------------------------------------------------------------");
            System.out.println();
            System.out.println("   1  View Laboratories");
            System.out.println("   2  Add New Laboratory");
            System.out.println();
            System.out.println("   0  Back to Main Menu");
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.print(" Select an option > ");

            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    displayLaboratories();
                    break;

                case "2":
                    addNewLaboratory();
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select 0, 1 or 2.");
            }
        }
    }

    // Display Laboratories
    private void displayLaboratories() {
        System.out.println();
        System.out.println("              LABORATORY LIST               ");
        System.out.println("+-----------------------------+------------+");
        System.out.printf(
                "| %-27s | %-10s |%n",
                "Laboratory",
                "Cost (RM)"
        );
        System.out.println("+-----------------------------+------------+");

        for (int i = 0; i < laboratories.size(); i++) {
            laboratories.get(i).labList();
        }
        System.out.println("+-----------------------------+------------+");
    }

    // Add New Laboratory
    private void addNewLaboratory() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("                     ADD NEW LABORATORY                     ");
        System.out.println("------------------------------------------------------------");

        Lab newLaboratory = new Lab();
        newLaboratory.newLab();
        laboratories.add(newLaboratory);

        System.out.println();
        System.out.println(" Laboratory added successfully.");
        System.out.println();
        System.out.println(" NEW RECORD");
        System.out.println("+-----------------------------+------------+");
        System.out.printf(
                "| %-27s | %-10s |%n",
                "Laboratory",
                "Cost (RM)"
        );
        System.out.println("+-----------------------------+------------+");
        newLaboratory.labList();
        System.out.println("+-----------------------------+------------+");
    }


    // Facility Menu
    private void facilityMenu() {
        boolean back = false;
        while (!back) {

            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                        FACILITY MENU                       ");
            System.out.println("------------------------------------------------------------");
            System.out.println();
            System.out.println("   1  View Facilities");
            System.out.println("   2  Add New Facility");
            System.out.println("   3  Ambulance Service");
            System.out.println();
            System.out.println("   0  Back to Main Menu");
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.print(" Select an option > ");

            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    displayFacilities();
                    break;

                case "2":
                    addNewFacility();
                    break;

                case "3":
                    ambulanceMenu();
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select from 0 to 3.");
            }
        }
    }

    // Display Facilities
    private void displayFacilities() {
        System.out.println();
        System.out.println("          FACILITY LIST           ");
        System.out.println("+--------------------------------+");
        System.out.printf(
                "| %-30s |%n",
                "Facility"
        );
        System.out.println("+--------------------------------+");

        for (int i = 0; i < facilities.size(); i++) {
            facilities.get(i).showFacility();
        }

        System.out.println("+--------------------------------+");
    }

    // Add New Facility
    private void addNewFacility() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("                      ADD NEW FACILITY                      ");
        System.out.println("------------------------------------------------------------");

        Facility newFacility = new Facility();
        newFacility.newFacility();
        facilities.add(newFacility);

        System.out.println();
        System.out.println(" Facility added successfully.");
        System.out.println();
        System.out.println(" NEW RECORD");
        System.out.println("+--------------------------------+");
        System.out.printf(
                "| %-30s |%n",
                "Facility"
        );
        System.out.println("+--------------------------------+");
        newFacility.showFacility();
        System.out.println("+--------------------------------+");
    }

    // Ambulance Menu - Application Extension
    private void ambulanceMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                       AMBULANCE MENU                       ");
            System.out.println("------------------------------------------------------------");
            System.out.println();
            System.out.println("   1  View Ambulances");
            System.out.println("   2  Add New Ambulance");
            System.out.println();
            System.out.println("   0  Back to Facility Menu");
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.print(" Select an option > ");

            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    displayAmbulances();
                    break;

                case "2":
                    addNewAmbulance();
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select 0, 1 or 2.");
            }
        }
    }

    // Display Ambulances
    private void displayAmbulances() {
        System.out.println();
        System.out.println("                      AMBULANCE LIST                            ");
        System.out.println("+--------+----------------------+-----------------+------------+");
        System.out.printf(
                "| %-6s | %-20s | %-15s | %-10s |%n",
                "ID",
                "Name",
                "Vehicle No.",
                "Capacity"
        );
        System.out.println("+--------+----------------------+-----------------+------------+");

        for (int i = 0; i < ambulances.size(); i++) {
            ambulances.get(i).showAmbulanceInfo();
        }

        System.out.println("+--------+----------------------+-----------------+------------+");
    }

    // Add New Ambulance
    private void addNewAmbulance() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("                      ADD NEW AMBULANCE                     ");
        System.out.println("------------------------------------------------------------");
        
        ArrayList<String> existingIds = new ArrayList<>();
        for (Ambulance a : ambulances) {
            existingIds.add(a.getId());
        }

        Ambulance newAmbulance = new Ambulance();
        newAmbulance.newAmbulance(existingIds);
        ambulances.add(newAmbulance);

        System.out.println();
        System.out.println(" Ambulance added successfully.");
        System.out.println();
        System.out.println(" NEW RECORD");
        System.out.println("+--------+----------------------+-----------------+------------+");
        System.out.printf(
                "| %-6s | %-20s | %-15s | %-10s |%n",
                "ID",
                "Name",
                "Vehicle No.",
                "Capacity"
        );
        System.out.println("+--------+----------------------+-----------------+------------+");
        newAmbulance.showAmbulanceInfo();
        System.out.println("+--------+----------------------+-----------------+------------+");
    }

    // Staff Menu
    private void staffMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                         STAFF MENU                         ");
            System.out.println("------------------------------------------------------------");
            System.out.println();
            System.out.println("   1  View Staff");
            System.out.println("   2  Add New Staff");
            System.out.println();
            System.out.println("   0  Back to Main Menu");
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.print(" Select an option > ");

            String choice = InputValidator.readString("");
            switch (choice) {

                case "1":
                    displayStaffs();
                    break;

                case "2":
                    addNewStaff();
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println();
                    System.out.println(" Invalid option. Please select 0, 1 or 2.");
            }
        }
    }

    // Display Staff
    private void displayStaffs() {
        System.out.println();
        System.out.println("                         STAFF LIST                                           ");
        System.out.println("+--------+--------------------+------------------+--------------+------------+");
        System.out.printf(
                "| %-6s | %-18s | %-16s | %-12s | %-10s |%n",
                "ID",
                "Name",
                "Designation",
                "Sex",
                "Salary"
        );
        System.out.println("+--------+--------------------+------------------+--------------+------------+");

        for (int i = 0; i < staffs.size(); i++) {
            staffs.get(i).showStaffInfo();
        }
        System.out.println("+--------+--------------------+------------------+--------------+------------+");
    }

    // Add New Staff
    private void addNewStaff() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("                       ADD NEW STAFF                        ");
        System.out.println("------------------------------------------------------------");
        
        ArrayList<String> existingIds = new ArrayList<>();
        for (Staff s : staffs) {
            existingIds.add(s.getId());
        }

        Staff newStaff = new Staff();
        newStaff.newStaff(existingIds);
        staffs.add(newStaff);

        System.out.println();
        System.out.println(" Staff added successfully.");
        System.out.println();
        System.out.println(" NEW RECORD");
        System.out.println("+--------+--------------------+------------------+--------------+------------+");
        System.out.printf(
                "| %-6s | %-18s | %-16s | %-12s | %-10s |%n",
                "ID",
                "Name",
                "Designation",
                "Sex",
                "Salary"
        );
        System.out.println("+--------+--------------------+------------------+--------------+------------+");
        newStaff.showStaffInfo();
        System.out.println("+--------+--------------------+------------------+--------------+------------+");
    }

    // Getter Methods
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Medical> getMedicals() {
        return medicals;
    }

    public ArrayList<Lab> getLaboratories() {
        return laboratories;
    }

    public ArrayList<Facility> getFacilities() {
        return facilities;
    }

    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public ArrayList<Ambulance> getAmbulances() {
        return ambulances;
    }

    // Add Methods
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addMedical(Medical medical) {
        medicals.add(medical);
    }

    public void addLaboratory(Lab laboratory) {
        laboratories.add(laboratory);
    }

    public void addFacility(Facility facility) {
        facilities.add(facility);
    }

    public void addStaff(Staff staff) {
        staffs.add(staff);
    }

    public void addAmbulance(Ambulance ambulance) {
        ambulances.add(ambulance);
    }

    // Main Method
    public static void main(String[] args) {
        HospitalManagement hospital = new HospitalManagement();
        hospital.start();
    }
}