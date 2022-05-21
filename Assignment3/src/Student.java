public class Student implements Comparable<Student> {
    private String studentNumber;
    private String studentLastName;
    private String homeDepartment;
    private String program;
    private char year;

    public Student(String studentNumber, String studentLastName, String homeDepartment, String program, char year) {
        this.studentNumber = studentNumber;
        this.studentLastName = studentLastName;
        this.homeDepartment = homeDepartment;
        this.program = program;
        this.year = year;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getHomeDepartment() {
        return homeDepartment;
    }

    public void setHomeDepartment(String homeDepartment) {
        this.homeDepartment = homeDepartment;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public char getYear() {
        return year;
    }

    public void setYear(char year) {
        this.year = year;
    }

    @Override
    public int compareTo(Student o) {
        return studentLastName.compareToIgnoreCase(o.getStudentLastName());
    }

    @Override
    public String toString() {
        return studentNumber + studentLastName + homeDepartment + program + year;
    }
}
