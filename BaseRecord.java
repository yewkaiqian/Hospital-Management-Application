
public class BaseRecord {
	
	// Fields
    private String id;
    private String name;

 // Constructors
    public BaseRecord(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public BaseRecord() {
    }

    // Getter Methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter Methods
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}