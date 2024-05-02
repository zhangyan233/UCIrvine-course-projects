import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        String studentFileName = "Students.txt";
        String courseFileName = "Courses.txt";

        try {
            //1.create database according to existing files
            DataBase dataBase=new DataBase(studentFileName,courseFileName);

            //2. create components
            ListAllStudents listAllStudents=new ListAllStudents(dataBase);
            ListAllCourses listAllCourses=new ListAllCourses(dataBase);
            ListCoursesCompleted listCoursesCompleted=new ListCoursesCompleted(dataBase);
            ListStudentsRegistered listStudentsRegistered=new ListStudentsRegistered(dataBase);
            ListCoursesRegistered listCoursesRegistered=new ListCoursesRegistered(dataBase);
            CourseConflict courseConflict=new CourseConflict(dataBase);
            OverStudentInCourse overStudentInCourse=new OverStudentInCourse(dataBase);
            RegisterStudent registerStudent=new RegisterStudent(dataBase);
            LogOutput logOutput=new LogOutput();

            //3. register components
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/listAllStudents",listAllStudents);
            Naming.rebind("rmi://localhost/listAllCourses",listAllCourses);
            Naming.rebind("rmi://localhost/listCoursesCompleted",listCoursesCompleted);
            Naming.rebind("rmi://localhost/listStudentsRegistered",listStudentsRegistered);
            Naming.rebind("rmi://localhost/listCoursesRegistered",listCoursesRegistered);
            Naming.rebind("rmi://localhost/courseConflict",courseConflict);
            Naming.rebind("rmi://localhost/overStudentInCourse",overStudentInCourse);
            Naming.rebind("rmi://localhost/registerStudent",registerStudent);
            Naming.rebind("rmi://localhost/logOutput",logOutput);

            System.err.println("Server ready");
        } catch (IOException e) {
            System.err.println("Server exception: "+ e.toString());
            e.printStackTrace();
        }

    }
}
