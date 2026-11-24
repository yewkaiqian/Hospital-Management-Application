import java.util.List;
import java.util.Scanner;

public class InputValidator {
	
	// Scanner
    private static Scanner input = new Scanner(System.in);

    // Read Integer Input with Exception Handling
    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(input.nextLine());
                return value;
            } 
            
            catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    // Read Non-Empty String
    public static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
    
    //Read Positive Integer
    public static int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);
            if (value > 0) {
                return value;
            }
            
            System.out.println("Value must be greater than 0.");
        }
    }

    // Read String Input
    public static String readString(String message) {
        System.out.print(message);
        return input.nextLine();
    }
    
    // Read a Non-Empty, Unique ID
    public static String readUniqueId(String message, List<String> existingIds) {
        while (true) {
            String value = readNonEmptyString(message);
 
            if (existingIds.contains(value)) {
                System.out.println("This ID already exists. Please enter a different ID.");
                continue;
            }
 
            return value;
        }
    }
    
    // Pure validation helpers for GUI(no console I/O).
    
    // Non-Empty Check
    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
 
    // Positive Integer Check
    public static boolean isPositiveInteger(String value) {
        if (!isNonEmpty(value)) {
            return false;
        }
 
        try {
            return Integer.parseInt(value.trim()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
 
    // Unique, Non-Empty ID Check
    public static boolean isUniqueId(String value, List<String> existingIds) {
        return isNonEmpty(value) && !existingIds.contains(value.trim());
    }
}
 