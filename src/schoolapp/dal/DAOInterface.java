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

    /**
     * Returns false if the user hasn't registered his/hers attendance
     *
     * @param date
     * @return
     */
    boolean checkForDailtyAttendance(Calendar date);

    /**
     * Returns true if the user is on the school network
     *
     * @return
     */

    boolean checkForSchoolNetWork();

    /**
     * Returns all classes
     *
     * @return
     */

    ArrayList<SchoolClass> getSchoolClasses();

    /**
     * Returns the student with the given ID
     *
     * @param id
     * @return
     */
    Student getStudent(int id);

}
