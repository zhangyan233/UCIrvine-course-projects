import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * *The handler just check whether this course is conflict with other courses registered or completed by other students
 */
public class CourseConflict extends UnicastRemoteObject implements IActivity{
    private DataBase objDataBase;
    /**
     * Construct "List all courses" command event handler.
     *
     * @param objDataBase reference to the database object
     */
    public CourseConflict(DataBase objDataBase) throws RemoteException {
        super();
        this.objDataBase=objDataBase;
    }

    /**
     * check conflict*
     * @param param a string parameter for command
     * @return
     */
    @Override
    public String execute(String param) throws RemoteException {
        // Parse the parameters.
        StringTokenizer objTokenizer = new StringTokenizer(param);
        String sSID     = objTokenizer.nextToken();
        String sCID     = objTokenizer.nextToken();
        String sSection = objTokenizer.nextToken();

        // Get the student and course records.
        Student objStudent = this.objDataBase.getStudentRecord(sSID);
        Course objCourse = this.objDataBase.getCourseRecord(sCID, sSection);
        if (objStudent == null) {
            return "Invalid student ID";
        }
        if (objCourse == null) {
            return "Invalid course ID or course section";
        }

        // Check if the given course conflicts with any of the courses the student has registered.
        ArrayList vCourse = objStudent.getRegisteredCourses();
        for (int i=0; i<vCourse.size(); i++) {
            if (((Course) vCourse.get(i)).conflicts(objCourse)) {
                return "conflict";
            }
        }

        return "no conflicts";
    }
}
