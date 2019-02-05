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
        int dayOfMonth=Calendar.DAY_OF_MONTH;
        cal2.set(Calendar.DAY_OF_MONTH, dayOfMonth-1);
        cal3.set(Calendar.DAY_OF_MONTH, dayOfMonth-2);
        cal4.set(Calendar.DAY_OF_MONTH, dayOfMonth-3);
        cal5.set(Calendar.DAY_OF_MONTH, dayOfMonth-4);
        cal6.set(Calendar.DAY_OF_MONTH, dayOfMonth-5);
        // Adds 'em to the Student

        s.addAttendance(cal, true);
        s.addAttendance(cal2, true);
        s.addAttendance(cal3, true);
        s.addAttendance(cal4, false);
        s.addAttendance(cal5, true);
        s.addAttendance(cal6, true);
    }

    public Student getStudent()
    {

        return s;
    }

}
