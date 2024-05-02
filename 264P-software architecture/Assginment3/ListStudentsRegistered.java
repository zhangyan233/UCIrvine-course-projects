/**
 * @(#)ListStudentsRegisteredHandler.java
 *
 * Copyright: Copyright (c) 2003,2004 Carnegie Mellon University
 *
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * "List students who registered for a course" command event handler.
 */
public class ListStudentsRegistered extends UnicastRemoteObject implements IActivity {

    private DataBase objDataBase;
    /**
     * Construct "List all courses" command event handler.
     *
     * @param objDataBase reference to the database object
     */
    public ListStudentsRegistered(DataBase objDataBase) throws RemoteException {
        super();
        this.objDataBase=objDataBase;
    }

    /**
     * Process "List students who registered for a course" command event.
     *
     * @param param a string parameter for command
     * @return a string result of command processing
     */
    public String execute(String param) throws RemoteException {
        // Parse the parameters.
        StringTokenizer objTokenizer = new StringTokenizer(param);
        String sCID     = objTokenizer.nextToken();
        String sSection = objTokenizer.nextToken();

        // Get the list of students who registered for the given course.
        Course objCourse = this.objDataBase.getCourseRecord(sCID, sSection);
        if (objCourse == null) {
            return "Invalid course ID or course section";
        }
        ArrayList vStudent = objCourse.getRegisteredStudents();

        // Construct a list of student information and return it.
        String sReturn = "";
        for (int i=0; i<vStudent.size(); i++) {
            sReturn += (i == 0 ? "" : "\n") + ((Student) vStudent.get(i)).toString();
        }
        return sReturn;
    }
}