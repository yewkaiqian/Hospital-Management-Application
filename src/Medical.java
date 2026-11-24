
public class Medical {
	
	// Fields
    private String name;
    private String manufacturer;
    private String expiryDate;
    private int cost;
    private int count;

    // Constructors
    public Medical(String name, String manufacturer, String expiryDate, int cost, int count) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.cost = cost;
        this.count = count;
    }

    public Medical() {
    }

    // Input Medical Details
    public void newMedical() {
        name = InputValidator.readNonEmptyString("Enter Medical Name: ");
        manufacturer = InputValidator.readNonEmptyString("Enter Manufacturer: ");
        expiryDate = InputValidator.readNonEmptyString("Enter Expiry Date: ");
        cost = InputValidator.readPositiveInt("Enter Cost: ");
        count = InputValidator.readPositiveInt("Enter Count: ");
    }
    
    // Getter Methods
    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }

    // Display Medical Information
    public void findMedical() {
        System.out.printf(
                "| %-18s | %-18s | %-12s | %-8d | %-8d |%n",
                name,
                manufacturer,
                expiryDate,
                cost,
                count
        );
    }

	
}
