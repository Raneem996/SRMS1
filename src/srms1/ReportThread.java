
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ReportThread extends Thread {

    private final String fileName;
    private final Object fileLock;

    public ReportThread(String fileName, Object fileLock) {
        this.fileName = fileName;
        this.fileLock = fileLock;
        setName("ReportThread");
    }

    @Override
    public void run() {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                throw new FileNotFoundException("Missing file: " + fileName);
            }

            ArrayList<Student> loaded;
            synchronized (fileLock) {
                loaded = FileHandler.loadFromFile(fileName);
            }

            synchronized (fileLock) {
                System.out.println("\n[Report] Generating reports...");
                ReportGenerator.reportByGPA(loaded);
                ReportGenerator.reportByDepartment(loaded);
                ReportGenerator.reportByYear(loaded);
                System.out.println("[Report] Done.\n");
            }

        } catch (FileNotFoundException e) {
            ErrorHandler.log("Report failed (file not found)", e);

        } catch (Exception e) {
            ErrorHandler.log("Report failed (unexpected)", e);
        }
    }
}
