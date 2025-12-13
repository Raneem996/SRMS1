public class ErrorHandler {

    public static void log(String msg, Exception e) {
        System.out.println("❌ " + msg);
        System.out.println("   -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }

    public static void info(String msg) {
        System.out.println("ℹ️ " + msg);
    }
}

// Custom Exception
class InvalidIntervalException extends Exception {
    public InvalidIntervalException(String message) {
        super(message);
    }
}
