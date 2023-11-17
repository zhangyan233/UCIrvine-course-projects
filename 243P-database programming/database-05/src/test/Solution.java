package test;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        //initial database
        DatabaseManager database=new DatabaseManager();
        database.initialDatabase();

        //read the order from workplace
        Scanner scanner=new Scanner(System.in);

        //offer all options to users
        while(true){
            System.out.println("------------menu------------");
            System.out.println("1  add new students");
            System.out.println("2  add new course");
            System.out.println("3  enroll student in course");
            System.out.println("4  query to see which students are in all course");
            System.out.println("5  query to see which students are in a course");
            System.out.println("6  query to see which courses all students are in");
            System.out.println("7  query to see which courses a student enroll");
            System.out.println("8  query to see which courses and what time each course of a student on a given day");
            System.out.println("9  exit");

            System.out.println("please enter one option(1-9):");

            //judge whether the option is number
            int option= 0;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("invalid option, please enter again");
                System.out.println("please enter one option(1-8):");
                continue;
            }

            //judge whether the option is valid, if not, enter again until it rights
            while(option<1||option>9){
                System.out.println("invalid option, please enter again");
                System.out.println("please enter one option(1-8):");
                option=Integer.parseInt(scanner.nextLine());
            }

            //add student
            if(option==1){
                //enter name in order to get the name of newStudent
                System.out.println("please enter the full name of student:");
                String name = scanner.nextLine();
                database.addStudent(name);
            }

            //add course
            if(option==2){
                //enter name of course
                System.out.println("please enter the name of course:");
                String courseName = scanner.nextLine();

                //get and check day
                int day=checkDay(scanner,database);

                //the time of day of this course
                System.out.println("please enter the time in a day of course(00:00):");
                String time = scanner.nextLine();

                //check whether the time is valid
                while(Pattern.matches("^([0-2][0-3]|[0-1][0-9]):[0-5][0-9]+$",time)==false){
                    System.out.println("invalid time, please enter again");
                    System.out.println("please enter the time in a day of course(00:00):");
                    time=scanner.nextLine();
                }

                database.addCourse(courseName,day,time);

            }

            //make student enroll in course
            if(option==3){
                //get and check the entered studentID
                int studentID=studentIDGetAndCheck(scanner,database);

                //get and check the entered courseId;
                int courseID= courseIdGetAndCheck(scanner,database);

                database.enrollCourse(studentID,courseID);
            }

            //query to see which students are in all courses
            if(option==4){
                database.findStudentsInAllCourse();
            }

            //query to see which students are in a course
            if(option==5){
                int courseID= courseIdGetAndCheck(scanner,database);

                database.findStudentsInOneCourse(courseID);
            }

            //query to see which courses all student are in;
            if(option==6){
                database.findCoursesAllStudent();
            }

            //query to see which courses the students is in
            if(option==7){
                //get and check the studentID
                int studentID= studentIDGetAndCheck(scanner,database);

                database.findOneStudentInCourse(studentID);

            }

            //query to see which courses and what time each course of a student on a given day
            if(option==8){
                //check the studentID
                int studentID= studentIDGetAndCheck(scanner,database);

                //the day of week
                int day=checkDay(scanner,database);

                database.findStudentInTimeCourse(studentID,day);
            }

            //exit
            if(option==9){
                break;
            }
        }

    }

    //enter and check whether studentID is right
    public static int studentIDGetAndCheck(Scanner scanner,DatabaseManager database){
        //get the studentid, check whether the studentID exists
        System.out.println("please enter the studentID:");

        int studentID=Integer.parseInt(scanner.nextLine());

        //judge whether studentID exists
        while(database.checkStudent(studentID)==false){
            System.out.println("the studentID doesn't exist, please enter again");
            System.out.println("please enter the studentID:");
            studentID=Integer.parseInt(scanner.nextLine());
        }

        return studentID;
    }

    //enter and check whether courseID is right
    public static int courseIdGetAndCheck(Scanner scanner,DatabaseManager database){
        //get the courseid, check whether the studentID exists
        System.out.println("please enter the courseID:");

        int courseID=Integer.parseInt(scanner.nextLine());

        //judge whether courseId exists
        while(database.checkCourse(courseID)==false){
            System.out.println("the courseID doesn't exist, please enter again");
            System.out.println("please enter the courseID:");
            courseID=Integer.parseInt(scanner.nextLine());
        }

        return courseID;
    }

    //get and check whether day is valid
    public static int checkDay(Scanner scanner,DatabaseManager database){
        //the day of week of this course
        System.out.println("please enter the day in a week of course(1-7):");
        int day= Integer.parseInt(scanner.nextLine());

        //check whether the day is in the range of 1-7
        while(day<1||day>7){
            System.out.println("invalid day, please enter again:");
            System.out.println("please enter the day in a week of course(1-7):");
            day= Integer.parseInt(scanner.nextLine());
        }
        return day;
    }


}
