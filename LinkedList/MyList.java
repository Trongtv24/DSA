/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

/**
 *
 * @author Giang
 */
import java.util.ArrayList;
import java.util.List;

public class MyList {
    Node head, tail;

    public MyList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Student x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
        } else {
            tail.next = q;
            tail = q;
        }
    }

    public void edit(int id, String newName, double newMarks) {
        Node current = head;
        while (current != null) {
            if (current.student.getId() == id) {
                current.student.setName(newName);
                current.student.setMarks(newMarks);
                return;
            }
            current = current.next;
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void delete(int id) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.student.getId() == id) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                if (current.next == null) {
                    tail = previous;
                }
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void traverse() {
        Node p = head;
        while (p != null) {
            p.print();
            p = p.next;
        }
    }

    // Bubble Sort by ID
    public void sortById() {
        if (isEmpty()) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;

            while (current != null && current.next != null) {
                if (current.student.getId() > current.next.student.getId()) {
                    Student temp = current.student;
                    current.student = current.next.student;
                    current.next.student = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    // Quick Sort by Marks
    public void sortByMarks() {
         if (head != null) {
             head = quickSortHelper(head, getTail(head));
         }
     }
    private Node quickSortHelper(Node start, Node end) {
       // Kiểm tra điều kiện nếu start hoặc end là null
       if (start == null || end == null || start == end || start.next == end) {
           return start;  // Trả về start nếu danh sách không hợp lệ
       }
       // Tiến hành sắp xếp nếu các điều kiện trên không xảy ra
       Node pivotPrev = partition(start, end);
       quickSortHelper(start, pivotPrev);

       if (pivotPrev != null && pivotPrev == start) {
           quickSortHelper(pivotPrev.next, end);
       } else if (pivotPrev != null && pivotPrev.next != null) {
           quickSortHelper(pivotPrev.next.next, end);
       }

       return start;
   }

    private Node partition(Node start, Node end) {
        if (start == end || start == null || end == null) return start;

        Node pivotPrev = start;
        Node curr = start;
        double pivot = end.student.getMarks();

        while (start != end) {
            if (start.student.getMarks() >= pivot) {
                pivotPrev = curr;
                Student temp = curr.student;
                curr.student = start.student;
                start.student = temp;
                curr = curr.next;
            }
            start = start.next;
        }
        
        // Swap pivot to its correct position
        Student temp = curr.student;
        curr.student = end.student;
        end.student = temp;

        return pivotPrev;
    }

    // Helper function to get the tail of the list
    private Node getTail(Node node) {
        while (node != null && node.next != null) {
            node = node.next;
        }
        return node;
    }

    // Binary Search by ID
    public Student binarySearchById(int targetId) {
        sortById(); // Ensure list is sorted by ID
        List<Node> nodes = new ArrayList<>();
        Node current = head;

        while (current != null) {
            nodes.add(current);
            current = current.next;
        }

        int low = 0, high = nodes.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midId = nodes.get(mid).student.getId();

            if (midId == targetId) {
                return nodes.get(mid).student;
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // ID not found
    }

    // Linear Search by Marks
    public List<Student> linearSearchByMarks(double targetMarks) {
        List<Student> results = new ArrayList<>();
        Node current = head;

        while (current != null) {
            if (current.student.getMarks() == targetMarks) {
                results.add(current.student);
            }
            current = current.next;
        }
        return results;
    }
    public boolean isIdExist(int id) {
    Node current = head;
    while (current != null) {
        if (current.student.getId() == id) {
            return true; // ID đã tồn tại
        }
        current = current.next;
    }
    return false; // ID không tồn tại
}


}




