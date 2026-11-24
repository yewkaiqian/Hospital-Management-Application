import java.util.List;

public class Doctor extends BaseRecord {
	
	// Fields
    private String specialist;
    private String workTime;
    private String qualification;
    private int room;

    // Constructors
    public Doctor(String id, String name, String specialist, String workTime, String qualification, int room) {
        super(id, name);
        this.specialist = specialist;
        this.workTime = workTime;
        this.qualification = qualification;
        this.room = room;
    }

    public Doctor() {
        super();
    }

    // Input Doctor Details
    public void newDoctor(List<String> existingIds) {
        setId(InputValidator.readUniqueId("Enter Doctor ID: ", existingIds));
        setName(InputValidator.readNonEmptyString("Enter Doctor Name: "));
        specialist = InputValidator.readNonEmptyString("Enter Specialist: ");
        workTime = InputValidator.readNonEmptyString("Enter Work Time: ");
        qualification = InputValidator.readNonEmptyString("Enter Qualification: ");
        room = InputValidator.readPositiveInt("Enter Room Number: ");
    }
    
    // Getter Methods
    public String getSpecialist() {
        return specialist;
    }

    public String getWorkTime() {
        return workTime;
    }

    public String getQualification() {
        return qualification;
    }

    public int getRoom() {
        return room;
    }

    // Display Doctor Information
    public void showDoctorInfo() {
        System.out.printf(
                "| %-6s | %-18s | %-15s | %-11s | %-18s | %-6d |%n",
                getId(),
                getName(),
                specialist,
                workTime,
                qualification,
                room
        );
    }
}