import java.util.List;

public class Patient extends BaseRecord {
	
	// Fields
    private String disease;
    private String sex;
    private String admitStatus;
    private int age;

    // Constructors
    public Patient(String id, String name, String disease, String sex, String admitStatus, int age) {
        super(id, name);
        this.disease = disease;
        this.sex = sex;
        this.admitStatus = admitStatus;
        this.age = age;
    }

    public Patient() {
        super();
    }

    // Input Patient Details
    public void newPatient(List<String> existingIds) {
        setId(InputValidator.readUniqueId("Enter Patient ID:", existingIds));
        setName(InputValidator.readNonEmptyString("Enter Patient Name: "));
        disease = InputValidator.readNonEmptyString("Enter Disease: ");
        sex = InputValidator.readNonEmptyString("Enter Sex: ");
        admitStatus = InputValidator.readNonEmptyString("Enter Admit Status: ");
        age = InputValidator.readPositiveInt("Enter Age: ");
    }
    
    // Getter Methods
    public String getDisease() {
        return disease;
    }

    public String getSex() {
        return sex;
    }

    public String getAdmitStatus() {
        return admitStatus;
    }

    public int getAge() {
        return age;
    }

    // Display Patient Information
    public void showPatientInfo() {
        System.out.printf(
                "| %-6s | %-18s | %-15s | %-10s | %-12s | %-5d |%n",
                getId(),
                getName(),
                disease,
                sex,
                admitStatus,
                age
        );
    }
}