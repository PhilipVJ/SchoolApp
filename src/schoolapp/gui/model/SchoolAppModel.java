/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.gui.model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schoolapp.be.Attendance;
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

    public SchoolAppModel()
    {
        this.manager = new SchoolAppManager();
        s = manager.getStudent();
        oList = FXCollections.observableArrayList(s.getFullAttendance());
    }

    public Student getStudent()
    {
        return manager.getStudent();

    }

    public ObservableList<Attendance> getList()
    {
        return oList;
    }
    
    
}
