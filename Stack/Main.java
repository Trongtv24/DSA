/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stack.object;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author Giang
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyStack stack = new MyStack(10); // Giới hạn stack cho 10 sinh viên
        
        int choice = -1; // Khởi tạo giá trị mặc định
        

        do {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Add Student");
                System.out.println("2. View Top Student");
                System.out.println("3. Pop Student");
                System.out.println("4. View All Students");
                System.out.println("5. Edit Student");
                System.out.println("6. Sort Students");
                System.out.println("7. Search Student");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Loại bỏ ký tự xuống dòng còn sót lại

                switch (choice) {
                    case 1:
                        // Thêm sinh viên
                        addStudent(scanner, stack);
                        break;

                    case 2:
                        // Xem sinh viên ở đỉnh stack
                        Student topStudent = stack.peek();
                        if (topStudent != null) {
                            System.out.println("Top Student: ");
                            System.out.println(topStudent);
                        }
                        break;

                    case 3:
                        // Lấy sinh viên từ đỉnh stack
                        Student poppedStudent = stack.pop();
                        if (poppedStudent != null) {
                            System.out.println("Popped Student: ");
                            System.out.println(poppedStudent);
                        }
                        break;

                    case 4:
                        // Hiển thị tất cả sinh viên trong stack
                        System.out.println("All Students in Stack:");
                        stack.printAllStudents();
                        break;

                    case 5:
                        // Sửa thông tin sinh viên
                        editStudent(scanner, stack);
                        break;

                    case 6:
                        // Sắp xếp sinh viên
                        sortStudents(scanner, stack);
                        break;

                    case 7:
                        // Tìm kiếm sinh viên
                        searchStudent(scanner, stack);
                        break;

                    case 8:
                        // Thoát
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Xóa input lỗi khỏi bộ đệm
            }
        } while (choice != 9);

        scanner.close();
    }

    // Phương thức nhập sinh viên với try-catch cho từng trường nhập liệu
private static void addStudent(Scanner scanner, MyStack stack) {
    int id = 0;
    double marks = 0.0;
    String name;

    // Nhập ID
    while (true) {
        try {
            System.out.print("Enter Student ID: ");
            id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for ID.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    // Nhập Name
    while (true) {
        System.out.print("Enter Student Name: ");
        name = scanner.nextLine();
        if (!name.matches("[a-zA-Z ]+")) { // Kiểm tra nếu Name không phải chuỗi chữ
            System.out.println("Invalid input. Name should only contain letters.");
        } else {
            break;
        }
    }

    // Nhập Marks
    while (true) {
        try {
            System.out.print("Enter Student Marks: ");
            marks = scanner.nextDouble();
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks should be between 0 and 100.");
            }
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for Marks.");
            scanner.nextLine(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Thêm sinh viên vào stack
    stack.push(new Student(id, name, marks));
    System.out.println("Student added successfully.");
}

  private static void editStudent(Scanner scanner, MyStack stack) {
    int id;
    String newName;
    double newMarks;

    // Nhập ID sinh viên cần chỉnh sửa, kiểm tra ID có tồn tại không
    while (true) {
        try {
            System.out.print("Enter Student ID to edit: ");
            id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Kiểm tra ID có tồn tại trong danh sách không
            if (!stack.containsID(id)) {
                System.out.println("Student ID not found. Please enter a valid ID.");
                continue; // Quay lại để nhập lại ID
            }
            break; // Thoát vòng lặp khi ID hợp lệ
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for ID.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    // Nhập Name mới
    while (true) {
        System.out.print("Enter new Name: ");
        newName = scanner.nextLine();
        if (!newName.matches("[a-zA-Z ]+")) { // Kiểm tra nếu Name không phải chuỗi chữ
            System.out.println("Invalid input. Name should only contain letters.");
        } else {
            break;
        }
    }

    // Nhập Marks mới
    while (true) {
        try {
            System.out.print("Enter new Marks: ");
            newMarks = scanner.nextDouble();
            if (newMarks < 0 || newMarks > 100) {
                throw new IllegalArgumentException("Marks should be between 0 and 100.");
            }
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for Marks.");
            scanner.nextLine(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Chỉnh sửa thông tin sinh viên
    boolean edited = stack.editStudent(id, newName, newMarks);
    if (edited) {
        System.out.println("Student information updated.");
    }
}

 private static void sortStudents(Scanner scanner, MyStack stack) {
    if (stack.isEmpty()) {
        System.out.println("The stack is empty. No students to sort.");
        return;
    }

    while (true) {
        try {
            System.out.println("Choose sorting method:");
            System.out.println("1. Sort by ID (Bubble Sort)");
            System.out.println("2. Sort by Marks (Quick Sort)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                stack.bubbleSortByID();
                System.out.println("Students have been sorted by ID.");
                stack.display();
                break;
            } else if (choice == 2) {
                stack.quickSortByMarks(0, stack.getTop());
                System.out.println("Students have been sorted by Marks.");
                stack.display();
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
            scanner.nextLine(); // Clear the invalid input
        }
    }
}



private static void searchStudent(Scanner scanner, MyStack stack) {
    if (stack.isEmpty()) {
        System.out.println("The stack is empty. No students to search.");
        return;
    }

    while (true) {
        try {
            System.out.println("Choose search method:");
            System.out.println("1. Search by ID (Binary Search)");
            System.out.println("2. Search by Marks (Linear Search)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter the ID to search: ");
                int id = scanner.nextInt();

                if (!stack.containsID(id)) {
                    System.out.println("ID not found. Please try again.");
                } else {
                    System.out.println("Student found: " + stack.binarySearchByID(id));
                    break;
                }
            } else if (choice == 2) {
                System.out.print("Enter the marks to search: ");
                double marks = scanner.nextDouble();

                if (!stack.containsMarks(marks)) { // Check if any student has these marks
                    System.out.println("No student found with these marks. Please try again.");
                } else {
                    System.out.println("Students with marks " + marks + ":");

                    // Hiển thị chi tiết danh sách sinh viên tìm được
                    for (int i = 0; i <= stack.getTop(); i++) {
                        Student student = stack.getStack()[i].getInfo();
                        if (student.getMark() == marks) {
                            System.out.println("ID: " + student.getID() + ", Name: " + student.getName()
                                    + ", Mark: " + student.getMark() + ", Rank: " + student.getRank());
                        }
                    }
                    break;
                }
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
            scanner.nextLine(); // Clear the invalid input
        }
    }
}

}
