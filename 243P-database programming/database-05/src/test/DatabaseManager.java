package test;

import java.sql.*;

//reference:https://github.com/michaelnguyen26/UCI-MSWE/blob/main/SWE%20243P%20(Database%20Programming)/Module%205/ExerciseM5/src/StudentDatabase.java
//manage database, like creating, adding
public class DatabaseManager {
    //the url to connect MySQL
    private static final String url="jdbc:mysql://localhost:3306/?user=root&password=1234";


    //initial database,involving creating database
    public void initialDatabase(){
        Connection connection=null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");//register driver

            connection= DriverManager.getConnection(url);//connect with MySQL

            Statement statement = connection.createStatement();//create statement to hold all queries

            //create database
            statement.execute("create database if not exists student_course_schedule");

            //create studentDatabase, involves: id, name
            statement.execute("create table if not exists student_course_schedule.tb_student" +
                    "(" +
                    "student_id      int             primary key     auto_increment," +
                    "student_name    varchar(50)     not null" +
                    ")" );

            //create courseDatabase, involves id,name,day,time
            statement.execute("create table if not exists student_course_schedule.tb_course" +
                    "(" +
                    "course_id       int             primary key     auto_increment," +
                    "course_name     varchar(25)     not null," +
                    "course_day      int             not null," +
                    "course_time     varchar(10)           not null" +
                    ")");

            //create connectionDatabase,involves the connection between each student and each course
            statement.execute("create table if not exists student_course_schedule.tb_connection" +
                    "(" +
                    "student_id       int            not null," +
                    "course_id        int            not null," +
                    "constraint connection_fk_student foreign key (student_id) references tb_student(student_id)," +
                    "constraint connection_fk_course foreign key (course_id) references tb_course(course_id)"  +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //allow new student to enroll into the program
    public void addStudent(String name){
        try {

            Connection connection=DriverManager.getConnection(url);//connect to MySQL
            PreparedStatement addStatement = connection.prepareStatement("insert into student_course_schedule.tb_student(student_name) values(?)");//hold queries

            addStatement.setString(1,name);
            addStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //add course
    public void addCourse(String name,int day,String time){

        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into student_course_schedule.tb_course(course_name,course_day,course_time) values(?,?,?)");

            //get three parameters
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,day);
            preparedStatement.setString(3,time);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //check whether studentID is in the system
    public boolean checkStudent(int studentID){
        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("select student_name as name from student_course_schedule.tb_student where student_id=?");

            preparedStatement.setInt(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            //judge whether there is a result
            if (resultSet.next() ==true) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //check whether courseID is in the system
    public boolean checkCourse(int courseID){
        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("select course_name as name from student_course_schedule.tb_course where course_id=?");

            preparedStatement.setInt(1,courseID);
            ResultSet resultSet = preparedStatement.executeQuery();

            //the result of query, and judge whether it exists
            if(resultSet.next()==false){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //student enroll in courses
    public void enrollCourse(int studentID,int courseID){

        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into student_course_schedule.tb_connection(student_id,course_id) values(?,?)");

            //find two parameters
            preparedStatement.setInt(1,studentID);
            preparedStatement.setInt(2,courseID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //find all student in one course
    public void findStudentsInOneCourse(int courseId){
        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("select s.student_name as studentName, cs.course_name as courseName from student_course_schedule.tb_connection c " +
                            "join student_course_schedule.tb_student s on c.student_id=s.student_id " +
                            "join student_course_schedule.tb_course cs on c.course_id=cs.course_id " +
                            "where c.course_id=?");

            preparedStatement.setInt(1,courseId);
            ResultSet resultSet = preparedStatement.executeQuery();//the result of select

            //displace results
            while(resultSet.next()){
                System.out.println(resultSet.getString("CourseName")+":"+resultSet.getString("studentName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //query which students in all courses
    public void findStudentsInAllCourse(){
        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("select o.course_name as courseName,s.student_name as studentName " +
                            "from student_course_schedule.tb_connection con " +
                            "join student_course_schedule.tb_student s on con.student_id=s.student_id " +
                            "join student_course_schedule.tb_course o on o.course_id=con.course_id " +
                            "order by courseName;");

            //display the result
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("courseName")+" "+resultSet.getString("studentName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //query which courses a student in
    public void findOneStudentInCourse(int studentID){
        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("select s.student_name as studentName, cs.course_name as courseName " +
                            "from student_course_schedule.tb_connection c " +
                            "join student_course_schedule.tb_student s on c.student_id=s.student_id " +
                            "join student_course_schedule.tb_course cs on c.course_id=cs.course_id " +
                            "where c.student_id=?");

            preparedStatement.setInt(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();//the result of search

            //display the result
            while(resultSet.next()){
                System.out.println(resultSet.getString("studentName")+" "+resultSet.getString("courseName")+" ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //query which courses all students enroll
    public void findCoursesAllStudent(){
        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("select cs.course_name as courseName,s.student_name as studentName from student_course_schedule.tb_connection c " +
                            "join student_course_schedule.tb_student s on c.student_id=s.student_id " +
                            "join student_course_schedule.tb_course cs on c.course_id=cs.course_id " +
                            "order by s.student_id");

            ResultSet resultSet = preparedStatement.executeQuery();

            //display results
            while(resultSet.next()){
                System.out.println(resultSet.getString("studentName")+" "+resultSet.getString("courseName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //query to see which courses and what time each course of a student on a given day
    public void findStudentInTimeCourse(int studentID,int day){
        try {
            Connection connection=DriverManager.getConnection(url);
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("select s.student_name as studentName,cs.course_name as courseName,cs.course_time as courseTime " +
                            "from student_course_schedule.tb_connection c " +
                            "join student_course_schedule.tb_student s on c.student_id=s.student_id " +
                            "join student_course_schedule.tb_course cs on c.course_id=cs.course_id " +
                            "where s.student_id=? and cs.course_day=? " );

            preparedStatement.setInt(1,studentID);
            preparedStatement.setInt(2,day);

            //display the result
            ResultSet resultSet = preparedStatement.executeQuery();
            String introduce="";
            switch (day){
                case 1: introduce+="Monday: ";
                        break;
                case 2: introduce+="Tuesday: ";
                        break;
                case 3: introduce+="Wednesday: ";
                        break;
                case 4: introduce+="Thursday: ";
                        break;
                case 5: introduce+="Friday: ";
                        break;
                case 6: introduce+="Saturday: ";
                        break;
                default: introduce+="Sunday: ";
            }
            System.out.println(introduce);

            while(resultSet.next()){
                System.out.println(resultSet.getString("studentName")+" "+resultSet.getString("courseName")+": "+resultSet.getString("courseTime"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
