import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;

/**
 * This class used to announce when a course booked by over 3 students*
 */
public class OverStudentInCourse extends UnicastRemoteObject implements IActivity{


    private DataBase objDataBase;
    /**
     * Construct "List all courses" command event handler.
     *
     * @param objDataBase reference to the database object
     */
    public OverStudentInCourse(DataBase objDataBase) throws RemoteException {
        super();
        this.objDataBase=objDataBase;
    }

    /**
     * when a course booked by larger than 3 students, announce " The course is overbooked"*
     * @param param a string parameter for command
     * @return
     */
    @Override
    public String execute(String param) throws RemoteException {
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
