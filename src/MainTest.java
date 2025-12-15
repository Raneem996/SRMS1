import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
       manager.addStudent(new Student("1", "Sara", 4.5, "IT", 2022));
        manager.addStudent(new Student("2", "Lama", 3.9, "CS", 2023));
        manager.addStudent(new Student("3", "Reem", 4.2, "IT", 2022));

        FileHandler.saveToFile("students.csv", manager.getStudents());

      
        ArrayList<Student> loadedStudents =
                FileHandler.loadFromFile("students.csv");

      
        ReportGenerator.reportByGPA(loadedStudents);
        ReportGenerator.reportByDepartment(loadedStudents);
        ReportGenerator.reportByYear(loadedStudents);

        System.out.println("Program finished successfully");
    }
}
