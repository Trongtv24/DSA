/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stack.object;

import java.util.logging.Logger;

/**
 *
 * @author Giang
 */
public class Student {
    private int ID;
    private String Name;
    private double Mark;

    public Student(int ID, String Name, double Mark) {
        this.ID = ID;
        this.Name = Name;
        this.Mark = Mark;
    }
    private static final Logger LOG = Logger.getLogger(Student.class.getName());

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public void setMark(double Mark) {
        this.Mark = Mark;
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public double getMark() {
        return Mark;
    }
    public static Logger getLOG() {
        return LOG;
    }
    @Override
    public String toString() {
       return "Student{" + "ID = " + ID + ", Name = " + Name + ", Mark=" + Mark + ", Rank = " + getRank() + '}';
    }
    public String getRank() {
        if (getMark() > 0 && getMark() <= 5) {
            return "Fail";
        } else if (getMark() > 5 && getMark() <= 6.5) {
            return "Medium";
        } else if (getMark() > 6.5 && getMark() <= 7.5) {
            return "Good";
        } else if (getMark() > 7.5 && getMark() <= 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
      }
}


