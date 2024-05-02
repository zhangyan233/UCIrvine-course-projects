/**
 * @(#)ListAllStudentsHandler.java
 *
 * Copyright: Copyright (c) 2003,2004 Carnegie Mellon University
 *
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * "List all students" command event handler.
 */
public class ListAllStudents extends UnicastRemoteObject implements IActivity {

    private DataBase objDataBase;
    /**
     * Construct "List all courses" command event handler.
     *
     * @param objDataBase reference to the database object
     */
    public ListAllStudents(DataBase objDataBase) throws RemoteException {
        super();
        this.objDataBase=objDataBase;
    }

    /**
     * Process "List all students" command event.
     *
     * @param param a string parameter for command
     * @return a string result of command processing
     */
    public String execute(String param) throws RemoteException {
        // Get all student records.
        ArrayList vStudent = this.objDataBase.getAllStudentRecords();

        // Construct a list of student information and return it.
        String sReturn = "";
        for (int i=0; i<vStudent.size(); i++) {
            sReturn += (i == 0 ? "" : "\n") + ((Student) vStudent.get(i)).toString();
        }
        return sReturn;
    }
}