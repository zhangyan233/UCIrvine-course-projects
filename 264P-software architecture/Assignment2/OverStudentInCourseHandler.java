import java.util.StringTokenizer;

/**
 * This class used to announce when a course booked by over 3 students*
 */
public class OverStudentInCourseHandler extends CommandEventHandler{


    /**
     * Constructs a command event handler. At the time of creation, it subscribes to the given
     * command event by default.
     *
     * @param objDataBase    reference to the database object
     * @param iCommandEvCode command event code to receive the commands to process
     * @param iOutputEvCode  output event code to send the command processing result
     */
    public OverStudentInCourseHandler(DataBase objDataBase, int iCommandEvCode, int iOutputEvCode) {
        super(objDataBase, iCommandEvCode, iOutputEvCode);
    }

    /**
     * when a course booked by larger than 3 students, announce " The course is overbooked"*
     * @param param a string parameter for command
     * @return
     */
    @Override
    protected String execute(String param) {
        // Parse the parameters.
        StringTokenizer objTokenizer = new StringTokenizer(param);
        String sCID     = objTokenizer.nextToken();
        String sSection = objTokenizer.nextToken();

        // Get the course records.
        Course objCourse = this.objDataBase.getCourseRecord(sCID, sSection);
        if(objCourse.vRegistered.size()>3){
            return objCourse.getName()+" is overbooked";
        }

        return "";
    }
}
