/**
 * @(#)ListAllCoursesHandler.java
 *
 * Copyright: Copyright (c) 2003,2004 Carnegie Mellon University
 *
 */

import javax.xml.crypto.Data;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 * "List all courses" command event handler.
 */
public class ListAllCourses extends UnicastRemoteObject implements IActivity {
    private DataBase objDataBase;
    /**
     * Construct "List all courses" command event handler.
     *
     * @param objDataBase reference to the database object
     */
    public ListAllCourses(DataBase objDataBase) throws RemoteException {
        super();
        this.objDataBase=objDataBase;
    }

    /**
     * Process "List all courses" command event.
     *
     * @param param a string parameter for command
     * @return a string result of command processing
     */
    public String execute(String param) throws RemoteException {
        // Get all course records.
        ArrayList vCourse = this.objDataBase.getAllCourseRecords();

        // Construct a list of course information and return it.
        String sReturn = "";
        for (int i=0; i<vCourse.size(); i++) {
            sReturn += (i == 0 ? "" : "\n") + ((Course) vCourse.get(i)).toString();
        }
        return sReturn;
    }
}