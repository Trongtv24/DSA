/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stack.object;

/**
 *
 * @author Giang
 */
import java.util.Arrays;
import java.util.Comparator;

public class MyStack {
    int size;
    int top;
    Node[] stack;
   
    public MyStack(int size) {
        this.size = size;
        top = -1;
        stack = new Node[size];
    }

    public boolean isStackFull() {
        return top == size - 1;
    }

    public boolean isStackEmpty() {
        return top == -1;
    }

    public void push(Student student) {
        if (isStackFull()) {
            System.out.println("Stack full");
        } else {
            top++;
            stack[top] = new Node(student);
        }
    }
    public Student pop() {
        if (isStackEmpty()) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return stack[top--].getInfo();
        }
    }
    public Student peek() {
        if (isStackEmpty()) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return stack[top].getInfo();
        }
    }
    public void printAllStudents() {
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i].getInfo());
        }
    }
     public boolean editStudent(int id, String name, double mark) {
        for (int i = 0; i <= top; i++) {
            if (stack[i].getInfo().getID() == id) {
                stack[i].getInfo().setName(name);
                stack[i].getInfo().setMark(mark);
                return true;
            }
        }
        return false;
    }
    
    // Bubble Sort: Sort by ID
    public void bubbleSortByID() {
        for (int i = 0; i <= top; i++) {
            for (int j = 0; j < top - i; j++) {
                if (stack[j].getInfo().getID() > stack[j + 1].getInfo().getID()) {
                    Node temp = stack[j];
                    stack[j] = stack[j + 1];
                    stack[j + 1] = temp;
                }
            }
        }
        System.out.println("Students sorted by ID using Bubble Sort.");
    }

    // Quick Sort: Sort by Marks
    public void quickSortByMarks(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            quickSortByMarks(low, pi - 1);
            quickSortByMarks(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = stack[high].getInfo().getMark();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (stack[j].getInfo().getMark() <= pivot) {
                i++;
                Node temp = stack[i];
                stack[i] = stack[j];
                stack[j] = temp;
            }
        }
        Node temp = stack[i + 1];
        stack[i + 1] = stack[high];
        stack[high] = temp;
        return i + 1;
    }

    // Binary Search: Search by ID
    public Student binarySearchByID(int id) {
        int low = 0;
        int high = top;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midID = stack[mid].getInfo().getID();

            if (midID == id) {
                return stack[mid].getInfo();
            } else if (midID < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // Not found
    }

    // Linear Search: Search by Marks
    public Student linearSearchByMarks(double marks) {
        for (int i = 0; i <= top; i++) {
            if (stack[i].getInfo().getMark() == marks) {
                return stack[i].getInfo();
            }
        }
        return null; // Not found
    }
       // Getter for top index
    public int getTop() {
        return top;
    }
    public boolean containsID(int id) {
    for (int i = 0; i <= top; i++) {
        if (stack[i] != null && stack[i].getInfo().getID() == id) {
            return true; // ID tồn tại trong stack
        }
    }
    return false; // ID không tồn tại
}
    public boolean containsMarks(double marks) {
    for (int i = 0; i <= top; i++) {
        if (stack[i].getInfo().getMark() == marks) {
            return true; // Marks tồn tại trong stack
        }
    }
    return false; // Marks không tồn tại
}
      public boolean isEmpty() {
    return top == -1; // Nếu top = -1, tức là không có phần tử nào trong stack
}

    public void display() {
    if (isEmpty()) {
        System.out.println("The stack is empty.");
    } else {
        System.out.println("Students in stack:");
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i].getInfo().toString()); // In thông tin của mỗi sinh viên trong stack
        }
    }
}
    public Node[] getStack() {
    return stack;
}


}

