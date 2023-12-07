package entity;

//Class student
public class Student {
    private Integer IDNumber;
    private String LastName;
    private String homeDepartment;
    private String ProgramName;
    private Integer year;
    private Student left;//left pointer
    private Student right;//right pointer

    //default constructor
    public Student(Integer IDNumber, String lastName, String homeDepartment, String programName, Integer year) {
        this.IDNumber = IDNumber;
        LastName = lastName;
        this.homeDepartment = homeDepartment;
        ProgramName = programName;
        this.year = year;
        this.left = null;
        this.right = null;
    }

    //getter and setter
    public Integer getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(Integer IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getHomeDepartment() {
        return homeDepartment;
    }

    public void setHomeDepartment(String homeDepartment) {
        this.homeDepartment = homeDepartment;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Student getLeft() {
        return left;
    }

    public void setLeft(Student left) {
        this.left = left;
    }

    public Student getRight() {
        return right;
    }

    public void setRight(Student right) {
        this.right = right;
    }

    //override to_string Arrange the read data of each student and then output it to the file in the original format.
    @Override
    public String toString() {
        String stu="";
        stu+=IDNumber.toString();

        //fill lastName with white space
        int len=LastName.length();
        for(int i=1;i<=25-len;i++){
            LastName+=' ';
        }
        stu+=LastName;

        stu+=homeDepartment;

        //fill program with white space
        len=ProgramName.length();
        for(int i=1;i<=4-len;i++){
            ProgramName+=' ';
        }
        stu+=ProgramName;

        stu+= year.toString();

        return stu;
    }
}
