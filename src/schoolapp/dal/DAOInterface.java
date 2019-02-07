/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.dal;

import java.util.ArrayList;
import java.util.Calendar;
import schoolapp.be.SchoolClass;
import schoolapp.be.Student;

/**
 *
 * @author Philip
 */
public interface DAOInterface
{

    boolean checkForDailtyAttendance(Calendar date);

    boolean checkForSchoolNetWork();

    ArrayList<SchoolClass> getSchoolClasses();

    Student getStudent(int id);
    
}
