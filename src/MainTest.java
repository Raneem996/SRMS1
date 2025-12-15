import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        manager.addStudent(new Student("1", "Sara", 4.5, "CS", 2));
        manager.addStudent(new Student("2", "Lama", 3.9, "IT", 3));
        manager.addStudent(new Student("3", "Reem", 4.8, "CS", 2));

     
        FileHandler.saveToFile("students.csv", manager.getStudents());

    
        ArrayList<Student> loaded = FileHandler.loadFromFile("students.csv");

         
        ReportGenerator.reportByGPA(loaded);
        ReportGenerator.reportByDepartment(loaded);
        ReportGenerator.reportByYear(loaded);
    }
}
