public class Facility {
	
	// Fields
    private String facility;

    // Constructors
    public Facility() {
    }

    public Facility(String facility) {
        this.facility = facility;
    }

    // Getter Methods
    public String getFacility() {
        return facility;
    }

    // Input Facility Details
    public void newFacility() {
        facility = InputValidator.readNonEmptyString("Enter Facility: ");
    }

    // Display Facility Information
    public void showFacility() {
        System.out.printf(
                "| %-30s |%n",
                facility
        );
    }
}