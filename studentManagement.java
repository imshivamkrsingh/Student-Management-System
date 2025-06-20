import java.util.ArrayList;
import java.util.Scanner;

interface studentinterface {
    void student_input();
    void display();
}

class Student implements studentinterface {
    private int system_id;
    private String name;
    private String course;
    private int sem;

    @Override
    public void student_input() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Student Details");
        System.out.print("System ID: ");
        system_id = s.nextInt();
        s.nextLine();  // consume newline
        System.out.print("Name: ");
        name = s.nextLine();
        System.out.print("Course: ");
        course = s.nextLine();
        System.out.print("Semester: ");
        sem = s.nextInt();
    }

    @Override
    public void display() {
        System.out.println("\nStudent Details:");
        System.out.println("System ID: " + system_id);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Semester: " + sem);
    }

    public int getSystemId() {
        return system_id;
    }
}

class Exam extends Student {
    protected int sub1 , sub2, sub3, sub4, sub5;
    protected int total;
    protected float per;
    protected String grade;
    protected String result;

    public void examinput() {
        Scanner s = new Scanner(System.in);
        System.out.println("\nEnter Marks:");
        System.out.print("Subject 1: "); sub1 = s.nextInt();
        System.out.print("Subject 2: "); sub2 = s.nextInt();
        System.out.print("Subject 3: "); sub3 = s.nextInt();
        System.out.print("Subject 4: "); sub4 = s.nextInt();
        System.out.print("Subject 5: "); sub5 = s.nextInt();

        total = sub1 + sub2 + sub3 + sub4 + sub5;
        per = total / 5.0f;

        if (per >= 90) grade = "A";
        else if (per >= 75) grade = "B";
        else if (per >= 60) grade = "C";
        else if (per >= 50) grade = "D";
        else grade = "F";

        result = (sub1 >= 35 && sub2 >= 35 && sub3 >= 35 && sub4 >= 35 && sub5 >= 35) ? "Pass" : "Fail";
    }

    public void examDisplay() {
        if (sub1 == -1) {
            System.out.println("\nNo exam data available.");
            return;
        }
        super.display();
        System.out.println("\nExam Details:");
        System.out.println("Total Marks: " + total);
        System.out.println("Percentage: " + per + "%");
        System.out.println("Grade: " + grade);
        System.out.println("Result: " + result);
    }
}

class Fee extends Exam {
    private float tuitionFee = -1, examFee, totalFee;

    public void feeInput() {
        Scanner s = new Scanner(System.in);
        System.out.println("\nEnter Fee Details:");
        System.out.print("Tuition Fee: "); tuitionFee = s.nextFloat();
        System.out.print("Exam Fee: "); examFee = s.nextFloat();
        totalFee = tuitionFee + examFee;
    }

    public void feeDisplay() {
        if (tuitionFee == -1) {
            System.out.println("\nNo fee details available.");
            return;
        }
        System.out.println("\nFee Structure:");
        System.out.println("Tuition Fee: ₹" + tuitionFee);
        System.out.println("Exam Fee: ₹" + examFee);
        System.out.println("Total Fee: ₹" + totalFee);
    }

    @Override
    public void display() {
        super.display();
        examDisplay();
        feeDisplay();
    }
}

public class studentManagement {
    static ArrayList<Fee> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int ch;
        do {
            System.out.println("\nStudent Management ");
            System.out.println("1. Add Student Details");
            System.out.println("2. View Students Details");
            System.out.println("3. Add Exam Marks Details");
            System.out.println("4. View Exam Result Details");
            System.out.println("5. Add Fee Details Details");
            System.out.println("6. View Fee StructureDetails");
            System.out.println("7. View All Details of student");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    Fee f = new Fee();
                    f.student_input();
                    students.add(f);
                    System.out.println("Student Added Successfully!");
                    break;

                case 2:
                    for (Fee stu : students) {
                        ((Student) stu).display();
                    }
                    break;

                case 3:
                    System.out.print("Enter System ID to Add Marks: ");
                    int id1 = sc.nextInt();
                    boolean marksFound = false;
                    for (Fee stu : students) {
                        if (stu.getSystemId() == id1) {
                            stu.examinput();
                            System.out.println("Marks Entered Successfully!");
                            marksFound = true;
                            break;
                        }
                    }
                    if (!marksFound) System.out.println("Student Not Found!");
                    break;

                case 4:
                    System.out.print("Enter System ID to View Exam Result: ");
                    int id2 = sc.nextInt();
                    boolean foundExam = false;
                    for (Fee stu : students) {
                        if (stu.getSystemId() == id2) {
                            stu.examDisplay();
                            foundExam = true;
                            break;
                        }
                    }
                    if (!foundExam) System.out.println("Student Not Found!");
                    break;

                case 5:
                    System.out.print("Enter System ID to Add Fee Details: ");
                    int id3 = sc.nextInt();
                    boolean feeAdded = false;
                    for (Fee stu : students) {
                        if (stu.getSystemId() == id3) {
                            stu.feeInput();
                            System.out.println("Fee Details Entered Successfully!");
                            feeAdded = true;
                            break;
                        }
                    }
                    if (!feeAdded) System.out.println("Student Not Found!");
                    break;

                case 6:
                    System.out.print("Enter System ID to View Fee Structure: ");
                    int id4 = sc.nextInt();
                    boolean foundFee = false;
                    for (Fee stu : students) {
                        if (stu.getSystemId() == id4) {
                            stu.feeDisplay();
                            foundFee = true;
                            break;
                        }
                    }
                    if (!foundFee) System.out.println("Student Not Found!");
                    break;

                case 7:
                    System.out.print("Enter System ID to View All Details: ");
                    int id6 = sc.nextInt();
                    boolean foundAll = false;
                    for (Fee stu : students) {
                        if (stu.getSystemId() == id6) {
                            stu.display();
                            foundAll = true;
                            break;
                        }
                    }
                    if (!foundAll) System.out.println("Student Not Found!");
                    break;

                case 8:
                    System.out.println("Exiting System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid Choice. Try again.");
            }
        } while (ch != 8);
    }
}
