import java.util.List;

public class Ambulance extends BaseRecord {
	
	// Fields
    private String vehicleNo;
    private int capacity;

    // Constructors
    public Ambulance(String id, String name,String vehicleNo, int capacity) {
        super(id, name);
        this.vehicleNo = vehicleNo;
        this.capacity = capacity;
    }

    // Default Constructor
    public Ambulance() {
        super();
    }

    // Input Ambulance Details
    public void newAmbulance(List<String> existingIds) {
        setId(InputValidator.readUniqueId("Enter Ambulance ID: ", existingIds));
        setName(InputValidator.readNonEmptyString("Enter Ambulance Name: "));
        vehicleNo = InputValidator.readNonEmptyString("Enter Vehicle Number: ");
        capacity = InputValidator.readPositiveInt("Enter Capacity: ");
    }
    
    // Getter Methods
    public String getVehicleNo() {
        return vehicleNo;
    }

    public int getCapacity() {
        return capacity;
    }

    // Display Ambulance Information
    public void showAmbulanceInfo() {
        System.out.printf(
                "| %-6s | %-20s | %-15s | %-10d |%n",
                getId(),
                getName(),
                vehicleNo,
                capacity
        );
    }
}