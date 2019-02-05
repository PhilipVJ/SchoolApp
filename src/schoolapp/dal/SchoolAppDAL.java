/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.dal;

import java.util.ArrayList;
import java.util.Calendar;
import schoolapp.be.Attendance;
import schoolapp.be.Student;

/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppDAL
{

    private Student s;
    private Student b;
    private Student c;

    public SchoolAppDAL()
    {
        s = new Student("Wilhelm", 2, "wilhelm@msn.com");
        // Setup mockup dates
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        Calendar cal4 = Calendar.getInstance();
        Calendar cal5 = Calendar.getInstance();
        Calendar cal6 = Calendar.getInstance();
        int dayOfMonth = Calendar.DAY_OF_MONTH;
        cal2.set(Calendar.DAY_OF_MONTH, dayOfMonth - 1);
        cal3.set(Calendar.DAY_OF_MONTH, dayOfMonth - 2);
        cal4.set(Calendar.DAY_OF_MONTH, dayOfMonth - 3);
        cal5.set(Calendar.DAY_OF_MONTH, dayOfMonth - 4);
        cal6.set(Calendar.DAY_OF_MONTH, dayOfMonth - 5);
        // Adds 'em to the Student

        s.addAttendance(cal, true);
        s.addAttendance(cal2, true);
        s.addAttendance(cal3, true);
        s.addAttendance(cal4, false);
        s.addAttendance(cal5, true);
        s.addAttendance(cal6, true);

        c = new Student("Jens", 3, "jens@msn.com");
        // Setup mockup dates
        Calendar ccal = Calendar.getInstance();
        Calendar ccal2 = Calendar.getInstance();
        Calendar ccal3 = Calendar.getInstance();
        Calendar ccal4 = Calendar.getInstance();
        Calendar ccal5 = Calendar.getInstance();
        Calendar ccal6 = Calendar.getInstance();
        int cdayOfMonth = Calendar.DAY_OF_MONTH;
        ccal2.set(Calendar.DAY_OF_MONTH, dayOfMonth - 1);
        ccal3.set(Calendar.DAY_OF_MONTH, dayOfMonth - 2);
        ccal4.set(Calendar.DAY_OF_MONTH, dayOfMonth - 3);
        ccal5.set(Calendar.DAY_OF_MONTH, dayOfMonth - 4);
        ccal6.set(Calendar.DAY_OF_MONTH, dayOfMonth - 5);
        // Adds 'em to the Student

        c.addAttendance(cal, true);
        c.addAttendance(cal2, false);
        c.addAttendance(cal3, true);
        c.addAttendance(cal4, false);
        c.addAttendance(cal5, true);
        c.addAttendance(cal6, true);

        b = new Student("Carl", 4, "carl@msn.com");
        // Setup mockup dates
        Calendar bcal = Calendar.getInstance();
        Calendar bcal2 = Calendar.getInstance();
        Calendar bcal3 = Calendar.getInstance();
        Calendar bcal4 = Calendar.getInstance();
        Calendar bcal5 = Calendar.getInstance();
        Calendar bcal6 = Calendar.getInstance();
        int bdayOfMonth = Calendar.DAY_OF_MONTH;
        bcal2.set(Calendar.DAY_OF_MONTH, dayOfMonth - 1);
        bcal3.set(Calendar.DAY_OF_MONTH, dayOfMonth - 2);
        bcal4.set(Calendar.DAY_OF_MONTH, dayOfMonth - 3);
        bcal5.set(Calendar.DAY_OF_MONTH, dayOfMonth - 4);
        bcal6.set(Calendar.DAY_OF_MONTH, dayOfMonth - 5);
        // Adds 'em to the Student

        b.addAttendance(cal, true);
        b.addAttendance(cal2, false);
        b.addAttendance(cal3, true);
        b.addAttendance(cal4, false);
        b.addAttendance(cal5, false);
        b.addAttendance(cal6, true);
    }

    public Student getStudent()
    {
        return s;
    }
    
    public ArrayList<Student> getAllFromClass()
    {
        ArrayList<Student>allStudents = new ArrayList<>();
        allStudents.add(s);
        allStudents.add(b);
        allStudents.add(c);
        return allStudents;
    }

}
