/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.be;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Christian Occhionero
 */
public class Student extends Person
{

    private ArrayList<Attendance> fullAttendance;

    public Student(String name, int id, String email)
    {
        super(name, id, email);
        fullAttendance = new ArrayList<>();
    }

    public void addAttendance(Calendar theDate, boolean wasThere)
    {
        fullAttendance.add(new Attendance(theDate, wasThere));
    }

    public ArrayList<Attendance> getFullAttendance()
    {
        return fullAttendance;
    }
    
    public Double getAbsencePercentage()
    {
        double totalDays = fullAttendance.size();
        double daysPresent = 0;
        
        for(Attendance x:fullAttendance)
        {
           if(x.getWasThere().booleanValue()==true)
           {
               daysPresent++;
           }
        }

        return 100-daysPresent/totalDays*100;

    }

} 
