import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * *The handler just check whether this course is conflict with other courses registered or completed by other students
 */
public class CourseConflictHandler extends CommandEventHandler{
    /**
     * Constructs a command event handler. At the time of creation, it subscribes to the given
     * command event by default.
     *
     * @param objDataBase    reference to the database object
     * @param iCommandEvCode command event code to receive the commands to process
     * @param iOutputEvCode  output event code to send the command processing result
     */
    public CourseConflictHandler(DataBase objDataBase, int iCommandEvCode, int iOutputEvCode) {
        super(objDataBase, iCommandEvCode, iOutputEvCode);
    }

    /**
     * check conflict*
     * @param param a string parameter for command
     * @return
     */
    @Override
    protected String execute(String param) {
        // Parse the parameters.
        StringTokenizer objTokenizer = new StringTokenizer(param);
        String sSID     = objTokenizer.nextToken();
        String sCID     = objTokenizer.nextToken();
        String sSection = objTokenizer.nextToken();

        // Get the student and course records.
        Student objStudent = this.objDataBase.getStudentRecord(sSID);
        Course objCourse = this.objDataBase.getCourseRecord(sCID, sSection);
        if (objStudent == null) {
            return param+" "+"Invalid student ID";
        }
        if (objCourse == null) {
            return param+" "+"Invalid course ID or course section";
        }

        // Check if the given course conflicts with any of the courses the student has registered.
        ArrayList vCourse = objStudent.getRegisteredCourses();
        for (int i=0; i<vCourse.size(); i++) {
            if (((Course) vCourse.get(i)).conflicts(objCourse)) {
                return param+" "+"conflict";
            }
        }

        return param+" "+"no conflicts";
    }
}
