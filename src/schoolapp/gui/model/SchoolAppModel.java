/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.gui.model;

import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schoolapp.be.Attendance;
import schoolapp.be.SchoolClass;
import schoolapp.be.Student;
import schoolapp.bll.SchoolAppManager;

/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppModel
{

    private final SchoolAppManager manager;
    private Student s;
    private ObservableList<Attendance> oList;
    private ObservableList<SchoolClass> classList;

    public SchoolAppModel()
    {
        this.manager = new SchoolAppManager();
        s = manager.getStudent(1);
        oList = FXCollections.observableArrayList(s.getFullAttendance());
    }

    public Student getStudent(int id)
    {
        return manager.getStudent(1);

    }

    public ObservableList<Attendance> getList()
    {
        return oList;
    }
    
    public ObservableList<SchoolClass> getAllClasses()
    {
        
        classList = FXCollections.observableArrayList(manager.getSchoolClasses());
        System.out.println(""+classList.size());
        return classList;
    }
    
    public boolean checkForSchoolNetwork()
    {
        return manager.checkForSchoolNetwork();
    }

    public boolean checkForDailyAttendance(Calendar date)
    {
        return manager.checkForDailtyAttendance(date);
    }
    
    
}
