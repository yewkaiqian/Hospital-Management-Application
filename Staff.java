import java.util.List;

public class Staff {
	
	// Fields
    private String id;
    private String name;
    private String designation;
    private String sex;
    private int salary;

    // Constructors
    public Staff(String id, String name, String designation, String sex, int salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.sex = sex;
        this.salary = salary;
    }

    public Staff() {
    }

    // Input Staff Details
    public void newStaff(List<String> existingIds) {
        id = InputValidator.readUniqueId("Enter Staff ID: ", existingIds);
        name = InputValidator.readNonEmptyString("Enter Staff Name: ");
        designation = InputValidator.readNonEmptyString("Enter Designation: ");
        sex = InputValidator.readNonEmptyString("Enter Sex: ");
        salary = InputValidator.readPositiveInt("Enter Salary: ");

    }
    
    // Getter Methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getSex() {
        return sex;
    }

    public int getSalary() {
        return salary;
    }

    // Display Staff Information
    public void showStaffInfo() {
        System.out.printf(
                "| %-6s | %-18s | %-16s | %-12s | %-10d |%n",
                id,
                name,
                designation,
                sex,
                salary
        );
    }

}
