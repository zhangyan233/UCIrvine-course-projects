import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {


        try {
            //1. get all component
            IActivity listAllStudents= (IActivity) Naming.lookup("rmi://localhost/listAllStudents");
            IActivity listAllCourses=(IActivity) Naming.lookup("rmi://localhost/listAllCourses");
            IActivity listCoursesCompleted=(IActivity) Naming.lookup("rmi://localhost/listCoursesCompleted");
            IActivity listStudentsRegistered=(IActivity) Naming.lookup("rmi://localhost/listStudentsRegistered");
            IActivity listCoursesRegistered=(IActivity) Naming.lookup("rmi://localhost/listCoursesRegistered");
            IActivity courseConflict=(IActivity) Naming.lookup("rmi://localhost/courseConflict");
            IActivity overStudentInCourse=(IActivity) Naming.lookup("rmi://localhost/overStudentInCourse");
            IActivity registerStudent= (IActivity) Naming.lookup("rmi://localhost/registerStudent");
            IActivity logOutput= (IActivity) Naming.lookup("rmi://localhost/logOutput");


            // 2. Show available commands and get a choice.
            Scanner scanner=new Scanner(System.in);

            while(true) {
                System.out.println("\nStudent Registration System\n");
                logOutput.execute("\nStudent Registration System\n");
                System.out.println("1) List all students");
                logOutput.execute("1) List all students");
                System.out.println("2) List all courses");
                logOutput.execute("2) List all courses");
                System.out.println("3) List students who registered for a course");
                logOutput.execute("3) List students who registered for a course");
                System.out.println("4) List courses a student has registered for");
                logOutput.execute("4) List courses a student has registered for");
                System.out.println("5) List courses a student has completed");
                logOutput.execute("5) List courses a student has completed");
                System.out.println("6) Register a student for a course");
                logOutput.execute("6) Register a student for a course");
                System.out.println("x) Exit");
                logOutput.execute("x) Exit");
                System.out.println("\nEnter your choice and press return >> ");
                logOutput.execute("\nEnter your choice and press return >> ");

                //3. get input
                String sChoice = scanner.nextLine();

                // Execute command 1: List all students.
                if (sChoice.equals("1")) {
                    String execute = listAllStudents.execute("");
                    System.out.println(execute);
                    logOutput.execute(execute);
                    continue;
                }

                // Execute command 2: List all courses.
                if (sChoice.equals("2")) {
                    String execute = listAllCourses.execute("");
                    System.out.println(execute);
                    logOutput.execute(execute);
                    continue;
                }

                // Execute command 3: List students registered for a course.
                if (sChoice.equals("3")) {
                    // Get course ID and course section from user.
                    System.out.println("\nEnter course ID and press return >> ");
                    logOutput.execute("\nEnter course ID and press return >> ");
                    String sCID = scanner.nextLine();

                    System.out.println("\nEnter course section and press return >> ");
                    logOutput.execute("\nEnter course section and press return >> ");
                    String sSection = scanner.nextLine();

                    String execute = listStudentsRegistered.execute(sCID + " " + sSection);
                    System.out.println(execute);
                    logOutput.execute(execute);
                    continue;
                }

                // Execute command 4: List courses a student has registered for.
                if (sChoice.equals("4")) {
                    // Get student ID from user.
                    System.out.println("\nEnter student ID and press return >> ");
                    logOutput.execute("\nEnter student ID and press return >> ");
                    String sSID = scanner.nextLine();

                    String execute = listCoursesRegistered.execute(sSID);
                    System.out.println(execute);
                    logOutput.execute(execute);
                    continue;
                }

                // Execute command 5: List courses a student has completed.
                if (sChoice.equals("5")) {
                    // Get student ID from user.
                    System.out.println("\nEnter student ID and press return >> ");
                    logOutput.execute("\nEnter student ID and press return >> ");
                    String sSID = scanner.nextLine();

                    String execute = listCoursesCompleted.execute(sSID);
                    System.out.println(execute);
                    logOutput.execute(execute);
                    continue;
                }

                // Execute command 6: Register a student for a course.
                if (sChoice.equals("6")) {
                    // Get student ID, course ID, and course section from user.
                    System.out.println("\nEnter student ID and press return >> ");
                    logOutput.execute("\nEnter student ID and press return >> ");
                    String sSID=scanner.nextLine();

                    System.out.println("\nEnter course ID and press return >> ");
                    logOutput.execute("\nEnter course ID and press return >> ");
                    String sCID=scanner.nextLine();

                    System.out.println("\nEnter course section and press return >> ");
                    logOutput.execute("\nEnter course section and press return >> ");
                    String sSection=scanner.nextLine();

                    String infor = courseConflict.execute(sSID + " " + sCID + " " + sSection);
                    //check whether course conflicts
                    if(!infor.equals("no conflicts")){
                        System.out.println(infor);
                        logOutput.execute(infor);
                    }else{

                        // if no conflicts, register firstly, and then check overbook
                        String register = registerStudent.execute(sSID + " " + sCID + " " + sSection);
                        System.out.println(register);
                        logOutput.execute(register);

                        String execute = overStudentInCourse.execute(sCID + " " + sSection);
                        System.out.println(execute);
                        logOutput.execute(execute);
                    }
                    continue;
                }

                // Terminate this client.
                if (sChoice.equalsIgnoreCase("X")) {
                    break;
                }
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
