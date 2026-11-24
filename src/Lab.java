
public class Lab {
	
	// Fields
    private String lab;
    private int cost;

    // Constructors
    public Lab() {
    }

    public Lab(String lab, int cost) {
        this.lab = lab;
        this.cost = cost;
    }

    // Getter Methods
    public String getLab() {
        return lab;
    }

    public int getCost() {
        return cost;
    }

    // Input Laboratory Details
    public void newLab() {
        lab = InputValidator.readNonEmptyString("Enter Laboratory Name: ");
        cost = InputValidator.readPositiveInt("Enter Cost: ");
    }

    // Display Laboratory Information
    public void labList() {
        System.out.printf(
                "| %-27s | %-10d |%n",
                lab,
                cost
        );
    }
}