/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

import java.util.Scanner;
import java.util.List;
/**
 *
 * @author Giang
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyList ml = new MyList();
        int n;
        while (true) {
            try {
                System.out.print("Enter the number of students to add: ");
                n = Integer.parseInt(scanner.nextLine());
                if (n <= 0) throw new IllegalArgumentException("Number of students must be greater than 0.");
                break; // Thoát vòng lặp nếu nhập đúng
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        // Nhập thông tin học sinh
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Student " + (i + 1) + ":");
            int id;
            while (true) {
                try {
                    System.out.print("ID : ");
                    id = Integer.parseInt(scanner.nextLine());
                    if (id <= 0) throw new IllegalArgumentException("ID must be a positive integer.");
                    if (ml.isIdExist(id)) throw new IllegalArgumentException("ID already exists! Please enter a unique ID.");
                    break; // Thoát vòng lặp nếu nhập đúng và không trùng
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            String name;
            while (true) {
                try {
                    System.out.print("Name : ");
                    name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty.");
                    }
                    // Kiểm tra Name chỉ chứa chữ cái (và khoảng trắng)
                    if (!name.matches("[a-zA-Z ]+")) {
                        throw new IllegalArgumentException("Name must only contain letters and spaces.");
                    }
                    break; // Thoát vòng lặp nếu nhập đúng
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            double marks;
            while (true) {
                try {
                    System.out.print("Marks (0.0 - 10.0): ");
                    marks = Double.parseDouble(scanner.nextLine());
                    if (marks < 0.0 || marks > 10.0) throw new IllegalArgumentException("Marks must be between 0.0 and 10.0.");
                    break; // Thoát vòng lặp nếu nhập đúng
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            ml.add(new Student(id, name, marks));
        }
        // Hiển thị danh sách sinh viên sau khi nhập
        System.out.println("\nStudent list after entry:");
        ml.traverse();

        int choice;
        // User menu
        do {
            try{
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Search Student");
            System.out.println("6. Display Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            // Kiểm tra nhập số nguyên cho "choice"
            String input = scanner.nextLine(); // Đọc input dưới dạng chuỗi
            choice = Integer.parseInt(input); // Chuyển đổi sang số nguyên
            if (choice < 0 || choice > 6) {
                System.out.println("Invalid choice. Please select a number between 0 and 6.");
                continue;
            }
            switch (choice) {
               case 1:
                    addStudent(scanner, ml);
                    break;
                case 2:
                    editStudent(scanner, ml);
                    break;
                case 3:
                   deleteStudent(scanner, ml);
                   break;
                case 4:
                    sortStudents(scanner, ml);                    
                    break;
                case 5:
                    searchStudents(scanner,ml);
                    break;
                case 6:
                    System.out.println("Displaying all students:");
                    ml.traverse();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            } 
           } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number between 0 and 6.");
            choice = -1; // Đặt một giá trị không hợp lệ để tiếp tục vòng lặp
    }
            
        } while (choice != 0);

        scanner.close();
    }
    private static void addStudent(Scanner scanner, MyList ml) {
    int id;
    String name;
    double marks;

    // Nhập ID
    while (true) {
        try {
            System.out.print("Enter ID: ");
            id = Integer.parseInt(scanner.nextLine());
            if (id <= 0) throw new IllegalArgumentException("ID must be a positive integer.");
            if (ml.isIdExist(id)) throw new IllegalArgumentException("ID already exists! Please enter a unique ID.");
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter an integer.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Nhập tên
    while (true) {
          try {
                System.out.print("Name : ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty.");
                }
                // Kiểm tra Name chỉ chứa chữ cái (và khoảng trắng)
                if (!name.matches("[a-zA-Z ]+")) {
                    throw new IllegalArgumentException("Name must only contain letters and spaces.");
                }
                break; // Thoát vòng lặp nếu nhập đúng
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
    }

    // Nhập điểm
    while (true) {
        try {
            System.out.print("Enter Marks (0.0 - 10.0): ");
            marks = Double.parseDouble(scanner.nextLine());
            if (marks < 0.0 || marks > 10.0) throw new IllegalArgumentException("Marks must be between 0.0 and 10.0.");
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Thêm sinh viên
    ml.add(new Student(id, name, marks));
    System.out.println("Student added successfully!");
    ml.traverse();
    }
    
    private static void editStudent(Scanner scanner, MyList ml) {
    int editId;
    String newName;
    double newMarks;
                    
    // Bắt buộc phải nhập ID có trong danh sách
    while (true) {
        try {
            System.out.print("Enter the ID of the student to edit: ");
            editId = Integer.parseInt(scanner.nextLine());
            if (!ml.isIdExist(editId)) {
                throw new IllegalArgumentException("ID not found in the list. Please enter a valid ID.");
            }
            break; // Thoát vòng lặp nếu nhập đúng ID
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                }
            }
            // Tìm học sinh theo ID
            Node current = ml.head;
            while (current != null) {
                if (current.student.getId() == editId) {
                    System.out.println("Editing details for Student with ID: " + editId);

                    // Sửa tên
                    while (true) {
                        try {
                            System.out.print("Enter new name : ");
                            newName = scanner.nextLine().trim();
                            if (newName.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
                            if (!newName.matches("[a-zA-Z ]+")) throw new IllegalArgumentException("Name must only contain letters and spaces.");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Sửa điểm 
                    while (true) {
                        try {
                            System.out.print("Enter new marks (0-10): ");
                            newMarks = Double.parseDouble(scanner.nextLine());
                            if (newMarks < 0.0 || newMarks > 10.0) throw new IllegalArgumentException("Marks must be between 0 and 10.");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a number.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                // Cập nhật thông tin học sinh
                current.student.setName(newName);
                current.student.setMarks(newMarks);
                System.out.println("Student details updated successfully.");
                break;
            }
            current = current.next;
        }
        ml.traverse();
    }
    private static void deleteStudent(Scanner scanner, MyList ml) {
       int id;

       // Nhập ID cần xóa
       // Bắt buộc phải nhập ID có trong danh sách
       while (true) {
           try {
               System.out.print("Enter the ID of the student to delete: ");
               id = Integer.parseInt(scanner.nextLine());
               if (!ml.isIdExist(id)) throw new IllegalArgumentException("ID not found in the list. Please enter a valid ID.");
               break; // Thoát vòng lặp nếu nhập đúng ID
           } catch (NumberFormatException e) {
               System.out.println("Invalid input! Please enter an integer.");
           } catch (IllegalArgumentException e) {
               System.out.println(e.getMessage());
           }
       }

       ml.delete(id);
       System.out.println("Student with ID " + id + " deleted successfully!");
       ml.traverse();
   }
    private static void sortStudents(Scanner scanner, MyList ml) {
    int sortChoice;

    // Nhập lựa chọn sắp xếp
    while (true) {
        try {
            System.out.println("\nSort Options:");
            System.out.println("1. By ID (Bubble Sort)");
            System.out.println("2. By Marks (Quick Sort)");
            System.out.print("Enter your choice (1 or 2): ");
            sortChoice = Integer.parseInt(scanner.nextLine());

            // Kiểm tra lựa chọn hợp lệ
            if (sortChoice != 1 && sortChoice != 2) {
                throw new IllegalArgumentException("Invalid choice! Please enter 1 or 2.");
            }
            break; // Thoát vòng lặp nếu nhập đúng
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a numeric value (1 or 2).");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Gọi phương thức sắp xếp dựa trên lựa chọn
    if (sortChoice == 1) {
        ml.sortById();
        System.out.println("Students sorted by ID:");
        ml.traverse();
    } else if (sortChoice == 2) {
        ml.sortByMarks();
        System.out.println("Students sorted by Marks:");
        ml.traverse();
    }
}
    private static void searchStudents(Scanner scanner,MyList ml) {
    // Hỏi người dùng chọn loại tìm kiếm
    int searchType = -1;
    while (searchType != 1 && searchType != 2) {
        try {
            System.out.println("Search by:");
            System.out.println("1. Search by ID");
            System.out.println("2. Search by Marks");
            System.out.print("Enter your choice (1 or 2): ");
            searchType = Integer.parseInt(scanner.nextLine());
            if (searchType != 1 && searchType != 2) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number (1 or 2).");
        }
    }

    // Xử lý loại tìm kiếm đã chọn
    switch (searchType) {
        case 1: // Tìm kiếm theo ID
            int searchId = -1;
            boolean validIdInput = false;

            while (!validIdInput) {
                try {
                    System.out.print("Enter the ID to search: ");
                    searchId = Integer.parseInt(scanner.nextLine());
                    if (searchId <= 0) {
                        throw new IllegalArgumentException("ID must be a positive integer.");
                    }
                    validIdInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid integer ID.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Gọi phương thức binarySearchById
            Student student = ml.binarySearchById(searchId);
            if (student != null) {
                System.out.println("Student found: ");
                System.out.println("Id: " + student.getId() + "\tName: " + student.getName() + "\tMarks: " + student.getMarks() + "\tRanking: " + student.getRanking());
            } else {
                System.out.println("No student found with ID " + searchId);
            }
            break;

        case 2: // Tìm kiếm theo Marks
            double searchMarks = -1;
            boolean validMarksInput = false;

            while (!validMarksInput) {
                try {
                    System.out.print("Enter the Marks to search: ");
                    searchMarks = Double.parseDouble(scanner.nextLine());
                    if (searchMarks < 0 || searchMarks > 10) {
                        throw new IllegalArgumentException("Marks must be between 0 and 10.");
                    }
                    validMarksInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number for Marks.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Gọi phương thức linearSearchByMarks
            List<Student> students = ml.linearSearchByMarks(searchMarks);
            if (!students.isEmpty()) {
                System.out.println("Students found with Marks " + searchMarks + ":");
                for (Student foundStudent : students) {
                    System.out.println("Id: " + foundStudent.getId() + "\tName: " + foundStudent.getName() + "\tMarks: " + foundStudent.getMarks() + "\tRanking: " + foundStudent.getRanking());
                }
            } else {
                System.out.println("No students found with Marks " + searchMarks);
            }
            break;

        default:
            System.out.println("Invalid search type.");
    }
}

//       public static int inputInteger(String message, Scanner scanner) {
//            while (true) {
//                try {
//                    System.out.print(message);
//                    int value = Integer.parseInt(scanner.nextLine());
//                    if (value <= 0) throw new IllegalArgumentException("Value must be positive.");
//                    return value;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input! Please enter an integer.");
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//
//        public static double inputDouble(String message, Scanner scanner, double min, double max) {
//            while (true) {
//                try {
//                    System.out.print(message);
//                    double value = Double.parseDouble(scanner.nextLine());
//                    if (value < min || value > max) throw new IllegalArgumentException("Value must be between " + min + " and " + max + ".");
//                    return value;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input! Please enter a number.");
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//
//        public static String inputString(String message, Scanner scanner) {
//            while (true) {
//                try {
//                    System.out.print(message);
//                    String value = scanner.nextLine().trim();
//                    if (value.isEmpty()) throw new IllegalArgumentException("Input cannot be empty.");
//                    return value;
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
}
