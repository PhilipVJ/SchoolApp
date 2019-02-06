/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.bll;

import java.util.ArrayList;
import schoolapp.be.Attendance;
import schoolapp.be.SchoolClass;
import schoolapp.be.Student;
import schoolapp.dal.SchoolAppDAL;

/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppManager
{

    private final SchoolAppDAL dal;

    public SchoolAppManager()
    {
        this.dal = new SchoolAppDAL();
    }
    
    public Student getStudent()
    {
        return dal.getStudent();
    }
    
    public ArrayList<SchoolClass> getSchoolClasses()
    {
        return dal.getSchoolClasses();
    }
    
    public boolean checkForSchoolNetwork()
    {
        return dal.checkForSchoolNetWork();
               
    }

    public boolean checkForDailtyAttendance()
    {
       return dal.checkForDailtyAttendance();
    }
}
